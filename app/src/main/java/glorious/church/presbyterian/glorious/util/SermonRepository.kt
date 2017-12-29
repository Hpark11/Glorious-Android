package glorious.church.presbyterian.glorious.util

import glorious.church.presbyterian.glorious.model.Result
import io.reactivex.Flowable
import io.reactivex.Observable

class SermonRepository(val apiService: SermonAPI) {
    fun searchResult(): Flowable<Result> {
        return apiService.sermonsList(SermonAPI.sermonListId)
    }
}