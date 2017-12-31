package glorious.church.presbyterian.glorious.ui


import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment: Fragment() {
    val subscriptions = CompositeDisposable()

    override fun onDetach() {
        super.onDetach()
        subscriptions.clear()
    }
}
//
