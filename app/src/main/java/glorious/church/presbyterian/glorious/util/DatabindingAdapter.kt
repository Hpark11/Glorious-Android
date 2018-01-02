package glorious.church.presbyterian.glorious.util

import android.databinding.BindingAdapter
import android.widget.Button
import glorious.church.presbyterian.glorious.R

@BindingAdapter("isVideoPlaying")
fun setYoutubeVideoState(button: Button, isPlaying: Boolean) {
    val id: Int = if (isPlaying) R.drawable.youtube_pause_button_selector else R.drawable.youtube_play_button_selector
    button.background = button.context.resources.getDrawable(id)
}