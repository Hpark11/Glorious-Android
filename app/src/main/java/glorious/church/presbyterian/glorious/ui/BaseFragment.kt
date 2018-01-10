package glorious.church.presbyterian.glorious.ui

import android.support.v4.app.Fragment
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.util.PlayerType
import glorious.church.presbyterian.glorious.util.SharedRef
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment: Fragment() {
    protected val subscriptions = CompositeDisposable()
    protected var videoId: String = SermonAPI.messageIdMain
    protected lateinit var shared: SharedRef

    open var playerType: PlayerType = PlayerType.basic
        set(value) {
            field = value
            shared.playerStyle = value
        }

    override fun onDetach() {
        super.onDetach()
        subscriptions.clear()
    }
}