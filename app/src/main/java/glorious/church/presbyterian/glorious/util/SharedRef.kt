package glorious.church.presbyterian.glorious.util

import android.content.Context
import android.content.SharedPreferences

class SharedRef(
        private val context: Context
) {
    private val preferences: SharedPreferences
    init {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    var playerStyle: PlayerType
        get() {
            val style = preferences.getInt("playerStyle", 0)
            when(style) {
                PlayerType.youtube.value -> {
                    return PlayerType.youtube
                }
                else -> {
                    return PlayerType.basic
                }
            }
        }
        set(take) {
            val editor = preferences.edit()
            editor.clear()
            editor.putInt("playerStyle", take.value)
            editor.apply()
        }
}