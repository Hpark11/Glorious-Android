package glorious.church.presbyterian.glorious.util

import android.databinding.BindingAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import glorious.church.presbyterian.glorious.R

@BindingAdapter("isVideoPlaying")
fun setYoutubeVideoState(button: Button, isPlaying: Boolean) {
    val id: Int = if (isPlaying) R.drawable.youtube_pause_button_selector else R.drawable.youtube_play_button_selector
    button.background = button.context.resources.getDrawable(id)
}

@BindingAdapter("thumbnailUrl")
fun setThumbnail(imageView: ImageView, url: String?) {
    url?.let { Picasso.with(imageView.context).load(url).into(imageView) }
}

@BindingAdapter("sermonType")
fun setSermonType(textView: TextView, title: String) {
    //DateFormat.getDateInstance(DateFormat.MEDIUM).format(date) + info.replace("(말씀 *: *)([\\w-_?.]+)".toRegex(), "$2")
}

@BindingAdapter("uploader")
fun setUploader(textView: TextView, name: String) {
    val uploader = "올린이: $name"
    textView.text = uploader
}

@BindingAdapter("playerStyle")
fun setPlayerStyle(textView: TextView, style: PlayerType?) {
    var playerStyle: String = "플레이어 스타일: "
    style?.let {
        when(style) {
            PlayerType.youtube -> {
                playerStyle += "유튜브"
            }
            else -> {
                playerStyle += "기본"
            }
        }
    }
    textView.text = playerStyle
}