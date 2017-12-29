package glorious.church.presbyterian.glorious.controller.pulpit

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import glorious.church.presbyterian.glorious.MainSermonListViewModel
import glorious.church.presbyterian.glorious.model.Result
import glorious.church.presbyterian.glorious.util.SermonAPI
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PulpitListViewModel(
    context: Application
): AndroidViewModel(context) {

    lateinit var pulpitMessages: Flowable<Result>
    private val apiService = SermonAPI.createAPIService()

    fun setObservables() {
        pulpitMessages = apiService.sermonsList(SermonAPI.sermonListId)
                .flatMap { messages ->
                    Log.d(TAG, messages.toString())
                    Flowable.fromArray(messages)
                }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        val TAG = PulpitListViewModel::class.java.simpleName
    }
}