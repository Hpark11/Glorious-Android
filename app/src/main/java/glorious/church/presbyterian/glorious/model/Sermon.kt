package glorious.church.presbyterian.glorious.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageDetail(
        val url: String,
        val width: Int,
        val height: Int
)

data class Video(
        @SerializedName("kind") val kind: String,
        @SerializedName("videoId") val id: String
)

data class Thumbnails(
        @SerializedName("default") val minimum: ImageDetail,
        @SerializedName("medium") val medium: ImageDetail,
        @SerializedName("high") val high: ImageDetail,
        @SerializedName("standard") val great: ImageDetail?
)

data class Snippet(
        @SerializedName("publishedAt") val published: Date,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("thumbnails") val thumbnails: Thumbnails,
        @SerializedName("channelTitle") val uploader: String,
        @SerializedName("resourceId") val video: Video
)

data class Sermon(
        @SerializedName("kind") val kind: String,
        @SerializedName("etag") val tag: String,
        @SerializedName("id") val id: String,
        @SerializedName("snippet") val snippet: Snippet
)

data class Result(
        val kind: String,
        val nextPageToken: String,
        val items: List<Sermon>
)

