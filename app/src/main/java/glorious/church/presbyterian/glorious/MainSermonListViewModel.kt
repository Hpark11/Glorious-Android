package glorious.church.presbyterian.glorious

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.util.Log
import glorious.church.presbyterian.glorious.util.APIService
import glorious.church.presbyterian.glorious.util.Sermon
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by hpark_ipl on 2017. 11. 28..
 */

class MainSermonListViewModel(
        context: Application
): AndroidViewModel(context) {

    private val tag = this.javaClass.simpleName

    private val context: Context = context.applicationContext
    private val apiService = APIService.createAPIService()

    public lateinit var pulpitMessages: Observable<MutableList<Sermon>>
    public lateinit var centerMessages: Observable<Sermon>
    public lateinit var featuredMessages: Observable<Sermon>


    fun setObservables() {
        pulpitMessages = apiService.sermonsList(APIService.sermonListId, APIService.key)
                .flatMap { messages ->
                    Log.d(tag, messages.toString())
                    Observable.fromArray(messages)
                }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

        //




    }









}

