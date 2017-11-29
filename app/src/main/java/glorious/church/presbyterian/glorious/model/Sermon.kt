package glorious.church.presbyterian.glorious.model

/**
 * Created by hpark_ipl on 2017. 11. 29..
 */

data class Sermon(
        val id: String,
        val title: String,
        val description: String,
        val imagePath: String,
        val videoId: String
)
