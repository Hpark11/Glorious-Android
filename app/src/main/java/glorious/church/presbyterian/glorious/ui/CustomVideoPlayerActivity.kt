package glorious.church.presbyterian.glorious.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.databinding.ActivityCustomVideoPlayerBinding
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.util.setPlaybackEventListener
import glorious.church.presbyterian.glorious.util.setPlayerStateChangeListener

class CustomVideoPlayerActivity: YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    companion object {
        private val TAG: String = CustomVideoPlayerActivity::class.java.simpleName
        private val RECOVERY_REQUEST: Int = 1
    }

    private lateinit var mPlayer: YouTubePlayer
    private lateinit var mBinding: ActivityCustomVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_video_player)
//        mBinding.playButton.setOnClickListener {
//            mPlayer?.play()
//        }

        //ButterKnife.bind(this)
        mBinding.youTubePlayerView.initialize(SermonAPI.key, this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, restored: Boolean) {
        player?.let {
            mPlayer = it
            it.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

            it.setPlaybackEventListener {
                onBuffering { Log.d(TAG, "onBuffering") }
                onPaused {
                    mBinding.isPlaying = false
                    Log.d(TAG, "onPaused")
                }
                onSeekTo { Log.d(TAG, "onSeekTo") }
                onStopped {
                    mBinding.isPlaying = false
                    Log.d(TAG, "onStopped")
                }
                onPlaying {
                    mBinding.isPlaying = true
                    Log.d(TAG, "onPlaying")
                }
            }

            it.setPlayerStateChangeListener {
                onAdStarted { Log.d(TAG, "onAdStarted")  }
                onError { Log.d(TAG, "onError")  }
                onLoaded { Log.d(TAG, "onLoaded")  }
                onLoading { Log.d(TAG, "onLoading")  }
                onVideoEnded { Log.d(TAG, "onVideoEnded")  }
                onVideoStarted { Log.d(TAG, "onVideoStarted") }
            }

            if(!restored) { it.loadVideo(SermonAPI.messageId1Min) }
        }

        Log.d(TAG, "onInitializationSuccess")
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, reason: YouTubeInitializationResult?) {
        if(reason!!.isUserRecoverableError) {
            reason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            Toast.makeText(this, reason.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun onPlayButtonTapped(view: View) {
        if(mPlayer.isPlaying) {
            mPlayer.pause()
        } else {
            mPlayer.play()
        }
    }
}

