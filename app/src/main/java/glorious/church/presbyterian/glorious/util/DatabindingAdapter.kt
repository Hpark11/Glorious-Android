package glorious.church.presbyterian.glorious.util

import android.databinding.BindingAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.model.Snippet
import glorious.church.presbyterian.glorious.model.Thumbnails
import java.text.DateFormat

@BindingAdapter("isVideoPlaying")
fun setYoutubeVideoState(button: Button, isPlaying: Boolean) {
    val id: Int = if (isPlaying) R.drawable.youtube_pause_button_selector else R.drawable.youtube_play_button_selector
    button.background = button.context.resources.getDrawable(id)
}

@BindingAdapter("thumbnails")
fun setThumbnail(imageView: ImageView, thumbnails: Thumbnails) {
    thumbnails.great?.let {
        Picasso.with(imageView.context).load(it.url).into(imageView)
        return
    }

    Picasso.with(imageView.context).load(thumbnails.high.url).into(imageView)
}

@BindingAdapter("sermonType")
fun setSermonType(textView: TextView, snippet: Snippet) {
    val data: String
    if(snippet.uploader == "worldremnant") {
        data = DateFormat.getDateInstance(DateFormat.MEDIUM).format(snippet.published) +
            snippet.title.replace("""([\w\d., ]*)(20[\d]{2})[\W]*(2nd|2nd service|1st|1st service|[dD]istrict|Core|Biz|New *Year's *MSG[\d: ]*|Remnant Day/Core|Biz MSG/[\w ]+)(: *)([\w\W]*)""".toRegex(), "$3")
    } else {
        data = snippet.title
    }
    textView.text = data
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
