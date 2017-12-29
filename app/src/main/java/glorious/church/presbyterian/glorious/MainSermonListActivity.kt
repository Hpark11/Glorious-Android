package glorious.church.presbyterian.glorious

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import glorious.church.presbyterian.glorious.controller.BaseFragment
import glorious.church.presbyterian.glorious.controller.center.CenterMessageListFragment
import glorious.church.presbyterian.glorious.controller.misc.MiscMessageListFragment
import glorious.church.presbyterian.glorious.controller.pulpit.PulpitListFragment
import glorious.church.presbyterian.glorious.util.SermonRepositoryProvider
import glorious.church.presbyterian.glorious.util.obtainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main_sermon_list.*

class MainSermonListActivity : RxAppCompatActivity() {
    private val tag = this.javaClass.simpleName

    private lateinit var viewModel: MainSermonListViewModel

    private var subscriptions = CompositeDisposable()

    private enum class MsgType {
        pulpit,
        center,
        misc
    }

    private fun changeMessageListType(type: MsgType) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val selectedFragement: BaseFragment?

        when(type) {
            MsgType.pulpit -> { selectedFragement = CenterMessageListFragment() }
            MsgType.center -> { selectedFragement = MiscMessageListFragment() }
            MsgType.misc -> { selectedFragement = PulpitListFragment() }
        }

        transaction.replace(messageListView.id, selectedFragement)
        transaction.commit()
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

//        val repository = SermonRepositoryProvider.provideSermonRepository()
//
//        subscriptions.add(
//                repository.searchResult()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.io())
//                        .subscribe ({
//                            result ->
//                            Log.d("Result", "There are (${result.kind}) Java developers in Lagos")
//                        }, { error ->
//                            error.printStackTrace()
//                        })
//        )

        viewModel = obtainViewModel().apply {
            this.setObservables()
            subscriptions.add(this.pulpitMessages.subscribe({
               Log.d(tag, "${it.toString()}")
            }))
        }


        //        Observable.create(ObservableOnSubscribe<String> { e ->
        //            e.onNext("Hello world!")
        //            e.onComplete()
        //        }).subscribe({ s ->
        //            this.message.setText(s)
        //        }, { e ->
        //            this.message.setText(e.toString())
        //        })

        // --------------------------------------------------------

        //        Observable.create<String> { s ->
        //            s.onNext("Hello, World")
        //            s.onComplete()
        //        }.subscribe { o -> this.message.setText(o) }

        // --------------------------------------------------------

        //Observable.just("Hello, world~!~!").subscribe(message::setText).dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }

    private fun obtainViewModel(): MainSermonListViewModel = obtainViewModel(MainSermonListViewModel::class.java)
}

