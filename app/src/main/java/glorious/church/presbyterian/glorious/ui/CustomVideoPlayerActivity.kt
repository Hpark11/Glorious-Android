package glorious.church.presbyterian.glorious.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.databinding.ActivityCustomVideoPlayerBinding
import glorious.church.presbyterian.glorious.util.SermonAPI
import glorious.church.presbyterian.glorious.util.__PlaybackEventListener
import glorious.church.presbyterian.glorious.util.__PlayerStateChangeListener
import kotlinx.android.synthetic.main.activity_main_sermon_list.*

class CustomVideoPlayerActivity: YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    companion object {
        private val TAG: String = CustomVideoPlayerActivity::class.java.simpleName
        private val RECOVERY_REQUEST: Int = 1
    }

    private var mPlayer: YouTubePlayer? = null
    private lateinit var mBinding: ActivityCustomVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        youtubePlayerView.initialize(SermonAPI.key, this)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_video_player)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, restored: Boolean) {
        if(player == null) return

        mPlayer = player
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

        player.setPlaybackEventListener {

        }

        player.setPlayerStateChangeListener {

        }

        if(!restored) {
            player.loadVideo(SermonAPI.messageId1Min)
            player.play()
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
}

inline fun YouTubePlayer.setPlaybackEventListener(func: __PlaybackEventListener.() -> Unit) {
    val listener = __PlaybackEventListener()
    listener.func()
    setPlaybackEventListener(listener)
}

inline fun YouTubePlayer.setPlayerStateChangeListener(func: __PlayerStateChangeListener.() -> Unit) {
    val listener = __PlayerStateChangeListener()
    listener.func()
    setPlayerStateChangeListener(listener)
}