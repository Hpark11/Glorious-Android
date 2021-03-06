package glorious.church.presbyterian.glorious.util

import android.widget.SeekBar
import com.google.android.youtube.player.YouTubePlayer

enum class PlayerType constructor(val value: Int) {
    basic(0),
    youtube(1)
}

class __PlayerStateChangeListener: YouTubePlayer.PlayerStateChangeListener {
    private var _onAdStarted: (() -> Unit)? = null
    private var _onError: ((reason: YouTubePlayer.ErrorReason?) -> Unit)? = null
    private var _onLoaded: ((what: String?) -> Unit)? = null
    private var _onLoading: (() -> Unit)? = null
    private var _onVideoEnded: (() -> Unit)? = null
    private var _onVideoStarted: (() -> Unit)? = null

    override fun onAdStarted() {
        _onAdStarted?.invoke()
    }

    fun onAdStarted(func: () -> Unit) {
        _onAdStarted = func
    }

    override fun onError(p0: YouTubePlayer.ErrorReason?) {
        _onError?.invoke(p0)
    }

    fun onError(func: (reason: YouTubePlayer.ErrorReason?) -> Unit) {
        _onError = func
    }

    override fun onLoaded(p0: String?) {
        _onLoaded?.invoke(p0)
    }

    fun onLoaded(func: (what: String?) -> Unit) {
        _onLoaded = func
    }

    override fun onLoading() {
        _onLoading?.invoke()
    }

    fun onLoading(func: () -> Unit) {
        _onLoading = func
    }

    override fun onVideoEnded() {
        _onVideoEnded?.invoke()
    }

    fun onVideoEnded(func: () -> Unit) {
        _onVideoEnded = func
    }

    override fun onVideoStarted() {
        _onVideoStarted?.invoke()
    }

    fun onVideoStarted(func: () -> Unit) {
        _onVideoStarted = func
    }
}

class __PlaybackEventListener: YouTubePlayer.PlaybackEventListener {
    private var _onSeekTo: ((where: Int) -> Unit)? = null
    private var _onBuffering: ((isBuffering: Boolean) -> Unit)? = null
    private var _onPlaying: (() -> Unit)? = null
    private var _onStopped: (() -> Unit)? = null
    private var _onPaused: (() -> Unit)? = null

    override fun onSeekTo(p0: Int) {
        _onSeekTo?.invoke(p0)
    }

    fun onSeekTo(func: (where: Int) -> Unit) {
        _onSeekTo = func
    }

    override fun onBuffering(p0: Boolean) {
        _onBuffering?.invoke(p0)
    }

    fun onBuffering(func: (isBuffering: Boolean) -> Unit) {
        _onBuffering = func
    }

    override fun onPlaying() {
        _onPlaying?.invoke()
    }

    fun onPlaying(func: () -> Unit) {
        _onPlaying = func
    }

    override fun onStopped() {
        _onStopped?.invoke()
    }

    fun onStopped(func: () -> Unit) {
        _onStopped = func
    }

    override fun onPaused() {
        _onPaused?.invoke()
    }

    fun onPaused(func: () -> Unit) {
        _onPaused = func
    }
}

class __SeekBarProgressChangeListener: SeekBar.OnSeekBarChangeListener {
    private var _onProgressChanged: ((seekBar: SeekBar?, progress: Int, fromUser: Boolean) -> Unit)? = null
    private var _onStartTrackingTouch: ((seekBar: SeekBar?) -> Unit)? = null
    private var _onStopTrackingTouch: ((seekBar: SeekBar?) -> Unit)? = null

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        _onProgressChanged?.invoke(seekBar, progress, fromUser)
    }

    fun onProgressChanged(f: (seekBar: SeekBar?, progress: Int, fromUser: Boolean) -> Unit) {
        _onProgressChanged = f
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        _onStartTrackingTouch?.invoke(seekBar)
    }

    fun onStartTrackingTouch(f: (seekBar: SeekBar?) -> Unit) {
        _onStartTrackingTouch = f
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        _onStopTrackingTouch?.invoke(seekBar)
    }

    fun onStopTrackingTouch(f: (seekBar: SeekBar?) -> Unit) {
        _onStopTrackingTouch = f
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

inline fun SeekBar.setOnSeekBarChangeListener(func: __SeekBarProgressChangeListener.() -> Unit) {
    val listener = __SeekBarProgressChangeListener()
    listener.func()
    setOnSeekBarChangeListener(listener)
}

