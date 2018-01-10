package glorious.church.presbyterian.glorious.util

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.ui.MainSermonListActivity
class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_splash_screen)

        Handler().postDelayed(Runnable {
            this.startActivity(android.content.Intent(this, MainSermonListActivity::class.java))
            this.finish()
        }, 2200)
    }
}