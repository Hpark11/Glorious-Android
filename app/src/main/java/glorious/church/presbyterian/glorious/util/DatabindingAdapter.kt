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
        val type: String
        if(snippet.title.contains("""2 *nd""".toRegex())) {
            type = "2부 예배"
        } else if (snippet.title.contains("""1 *st""".toRegex())) {
            type = "1부 예배"
        } else if (snippet.title.contains("""[dD]istrict""".toRegex())) {
            type = "구역 예배"
        } else if (snippet.title.contains("Core")) {
            type = "핵심"
        } else if (snippet.title.contains("Biz")) {
            type = "산업선교"
        } else if (snippet.title.contains("""New *Year's *MSG *2""".toRegex())) {
            type = "송구영신 3부"
        } else if (snippet.title.contains("""New *Year's *MSG *1""".toRegex())) {
            type = "송구영신 2부"
        } else if (snippet.title.contains("""New *Year's *MSG""".toRegex())) {
            type = "송구영신 1부"
        } else if (snippet.title.contains("Christmas")) {
            type = "성탄 예배"
        } else if (snippet.title.contains("Remnant Day")) {
            type = "렘넌트데이"
        } else {
            type = "예배"
        }

        data = "임마누엘교회 ${DateFormat.getDateInstance(DateFormat.MEDIUM).format(snippet.published)}\n본부 $type"
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
