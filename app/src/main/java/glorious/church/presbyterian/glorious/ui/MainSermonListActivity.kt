package glorious.church.presbyterian.glorious.ui


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.ui.center.CenterMessageListFragment
import glorious.church.presbyterian.glorious.ui.misc.MiscMessageListFragment
import glorious.church.presbyterian.glorious.ui.pulpit.PulpitListFragment
import glorious.church.presbyterian.glorious.util.PlayerType
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_main_sermon_list.*

class MainSermonListActivity : RxAppCompatActivity() {
    companion object {
        private val TAG = this::class.java.simpleName
    }

    private lateinit var selectedFragement: BaseFragment
    private var mType: MsgType = MsgType.misc
    private var subscriptions = CompositeDisposable()

    private enum class MsgType {
        pulpit,
        center,
        misc
    }

    private fun changeMessageListType(type: MsgType) {
        if(type == mType) return
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        when(type) {
            MsgType.pulpit -> {
                selectedFragement = PulpitListFragment()
                supportActionBar?.title = getString(R.string.title_home)
            }
            MsgType.center -> {
                selectedFragement = CenterMessageListFragment()
                supportActionBar?.title = getString(R.string.title_dashboard)
            }
            MsgType.misc -> {
                selectedFragement = MiscMessageListFragment()
                supportActionBar?.title = getString(R.string.title_notifications)
            }
        }

        transaction.replace(messageListView.id, selectedFragement)
        transaction.commit()
        mType = type
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeMessageListType(MsgType.pulpit)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                changeMessageListType(MsgType.center)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                changeMessageListType(MsgType.misc)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sermon_list)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        changeMessageListType(MsgType.pulpit)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_type_video, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                R.id.basicStyle -> {
                    selectedFragement.playerType = PlayerType.basic
                }
                R.id.youtubeStyle -> {
                    selectedFragement.playerType = PlayerType.youtube
                    return true
                } else -> {
                    return false
                }
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }

    //private fun obtainViewModel(): MainSermonListViewModel = obtainViewModel(MainSermonListViewModel::class.java)
}

