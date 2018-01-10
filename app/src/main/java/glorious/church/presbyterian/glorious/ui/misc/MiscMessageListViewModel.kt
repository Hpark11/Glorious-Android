package glorious.church.presbyterian.glorious.ui.misc

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import glorious.church.presbyterian.glorious.model.Result
import glorious.church.presbyterian.glorious.repository.SermonAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function5
import io.reactivex.schedulers.Schedulers

class MiscMessageListViewModel(
        context: Application
): AndroidViewModel(context) {
    companion object {
        private val TAG = MiscMessageListViewModel::class.java.simpleName
    }

    lateinit var miscMessages: Observable<Result>
    private val apiService = SermonAPI.createAPIService()
    val titles = listOf<String>("62가지", "강단 구원의 길", "1분 구원의 길", "3분 구원의 길", "5분 구원의 길")

    fun setObservables() {
        miscMessages = Observable.zip(
                        apiService.featuredSermon(SermonAPI.messageId62),
                        apiService.featuredSermon(SermonAPI.messageIdMain),
                        apiService.featuredSermon(SermonAPI.messageId1Min),
                        apiService.featuredSermon(SermonAPI.messageId3Min),
                        apiService.featuredSermon(SermonAPI.messageId5Min),
                        Function5<Result, Result, Result, Result, Result, Result> { a, b, c, d, e ->
                            Result(null, null, items = a.items + b.items + c.items + d.items + e.items)
                        }
                )
                .flatMap { messages ->
                    Log.d(TAG, messages.toString())
                    Observable.fromArray(messages)
                }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}