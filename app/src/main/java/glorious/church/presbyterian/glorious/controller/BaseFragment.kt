package glorious.church.presbyterian.glorious.controller


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment: Fragment() {
    protected val subscriptions = CompositeDisposable()

    override fun onDetach() {
        super.onDetach()
        subscriptions.clear()
    }
}
//
