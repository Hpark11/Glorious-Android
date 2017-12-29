package glorious.church.presbyterian.glorious

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.util.Log
import glorious.church.presbyterian.glorious.model.Result
import glorious.church.presbyterian.glorious.model.Sermon
import glorious.church.presbyterian.glorious.util.SermonAPI
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainSermonListViewModel(
        context: Application
): AndroidViewModel(context) {

    private val tag = this.javaClass.simpleName

    private val context: Context = context.applicationContext
    private val apiService = SermonAPI.Factory.createAPIService()

    public lateinit var pulpitMessages: Flowable<Result>
    public lateinit var centerMessages: Observable<Sermon>
    public lateinit var featuredMessages: Observable<Sermon>

    //APIService.sermonListId
    //APIService.key
    fun setObservables() {
        pulpitMessages = apiService.sermonsList(SermonAPI.sermonListId)
                .flatMap { messages ->
                    Log.d(tag, messages.toString())
                    Flowable.fromArray(messages)
                }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

