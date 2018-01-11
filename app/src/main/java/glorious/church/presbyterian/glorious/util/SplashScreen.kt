package glorious.church.presbyterian.glorious.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.ui.MainSermonListActivity

class SplashScreen: AppCompatActivity() {
    private fun isConnectedTo(): Boolean {
        val cm: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (null != activeNetwork) {
            when(activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> return true
                ConnectivityManager.TYPE_MOBILE -> return true
                else -> return false
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_splash_screen)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed(Runnable {
            if(isConnectedTo()) {
                this.startActivity(android.content.Intent(this, MainSermonListActivity::class.java))
                this.finish()
            } else {
                showNetworkAlertDialog(this)
            }
        }, 2200)
    }
}