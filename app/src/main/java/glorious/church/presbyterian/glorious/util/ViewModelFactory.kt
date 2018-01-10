package glorious.church.presbyterian.glorious.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import glorious.church.presbyterian.glorious.ui.center.CenterMessageListViewModel
import glorious.church.presbyterian.glorious.ui.misc.MiscMessageListViewModel
import glorious.church.presbyterian.glorious.ui.pulpit.PulpitListViewModel

class ViewModelFactory private constructor(
        private val application: Application
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(PulpitListViewModel::class.java) -> PulpitListViewModel(application)
                    isAssignableFrom(CenterMessageListViewModel::class.java) -> CenterMessageListViewModel(application)
                    isAssignableFrom(MiscMessageListViewModel::class.java) -> MiscMessageListViewModel(application)
                    else -> throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
                }
            } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var instance: ViewModelFactory? = null

        fun getInstance(application: Application) =
                instance ?: synchronized(ViewModelFactory::class.java) {
                    instance ?: ViewModelFactory(application).also { instance = it }
                }

        @VisibleForTesting fun destroyInstance() {
            instance = null
        }
    }
}