package glorious.church.presbyterian.glorious.model

import com.google.gson.annotations.SerializedName

data class Sermon(
        @SerializedName("kind") val kind: String,
        @SerializedName("etag") val etag: String,
        @SerializedName("id") val id: String
//        val imagePath: String,
//        val videoId: String
)

data class Result(
        val kind: String,
        val nextPageToken: String,
        val items: List<Sermon>
)

