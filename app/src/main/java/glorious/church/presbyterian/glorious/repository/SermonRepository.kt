package glorious.church.presbyterian.glorious.repository

import glorious.church.presbyterian.glorious.model.Result
import io.reactivex.Flowable

class SermonRepository(val apiService: SermonAPI) {
    fun searchResult(): Flowable<Result> {
        return apiService.sermonsList(SermonAPI.sermonListId)
    }
}