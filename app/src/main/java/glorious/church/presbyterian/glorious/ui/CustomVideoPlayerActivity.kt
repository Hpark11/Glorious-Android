package glorious.church.presbyterian.glorious.ui

import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.databinding.ActivityCustomVideoPlayerBinding
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.util.setPlaybackEventListener
import glorious.church.presbyterian.glorious.util.setPlayerStateChangeListener
import glorious.church.presbyterian.glorious.util.setOnSeekBarChangeListener
import java.util.*
import kotlin.concurrent.timerTask

class CustomVideoPlayerActivity: YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    companion object {
        private val TAG: String = CustomVideoPlayerActivity::class.java.simpleName
        private val RECOVERY_REQUEST: Int = 1
    }

    private lateinit var mPlayer: YouTubePlayer
    private lateinit var mBinding: ActivityCustomVideoPlayerBinding
    private var mProgressTimer: Timer = Timer()
    private var videoId: String = SermonAPI.messageIdMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoId = intent.getStringExtra("videoId")

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_video_player)
        mBinding.youTubePlayerView.initialize(SermonAPI.key, this)
        mBinding.youtubePlayerSeekBar.setOnSeekBarChangeListener {
            onProgressChanged { seekBar, progress, fromUser ->
                if (fromUser) {
                    mPlayer.seekToMillis(progress)
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, restored: Boolean) {
        player?.let {
            mPlayer = it
            it.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

            it.setPlaybackEventListener {
                onBuffering { Log.d(TAG, "onBuffering") }
                onPaused {
                    Log.d(TAG, "onPaused")
                    mBinding.isPlaying = false
                    mProgressTimer.cancel()
                }
                onSeekTo { where ->
                    Log.d(TAG, "onSeekTo")
                }
                onStopped {
                    Log.d(TAG, "onStopped")
                    mBinding.isPlaying = false
                    mProgressTimer.cancel()
                }
                onPlaying {
                    Log.d(TAG, "onPlaying")
                    mBinding.isPlaying = true
                    mProgressTimer = Timer()
                    mProgressTimer.schedule(timerTask {
                        mBinding.youtubePlayerSeekBar.progress = mPlayer.currentTimeMillis
                    }, 0, 100)
                }
            }

            it.setPlayerStateChangeListener {
                onAdStarted { Log.d(TAG, "onAdStarted")  }
                onError { Log.d(TAG, "onError")  }
                onLoaded { Log.d(TAG, "onLoaded")  }
                onLoading { Log.d(TAG, "onLoading")  }
                onVideoEnded { Log.d(TAG, "onVideoEnded")  }
                onVideoStarted {
                    mBinding.youtubePlayerSeekBar.max = mPlayer.durationMillis
                    Log.d(TAG, "onVideoStarted")
                }
            }

            if(!restored) {
                it.loadVideo(videoId)
            }
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

    override fun onStop() {
        super.onStop()
        mProgressTimer.cancel()
    }
}




