package glorious.church.presbyterian.glorious.ui.pulpit

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import glorious.church.presbyterian.glorious.model.Result
import glorious.church.presbyterian.glorious.repository.SermonAPI
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.util.*

class PulpitListViewModel(
    context: Application
): AndroidViewModel(context) {
    companion object {
        private val TAG = PulpitListViewModel::class.java.simpleName
    }

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

    val extractedTitle: (String) -> (String) = { title ->
        val first = title.replace("""([\w\W]*)(제목 *: *[\w\W]*)""".toRegex(), "$2")
        val second = first.replace("""(말씀 *: *)([\w\W\n]*)""".toRegex(), "")
        second.replace("""(제목 *: *)([\w-_?.]+)""".toRegex(), "$2")
    }

    val extractedSubInfo: (String, Date) -> (String) = { info, date ->
        DateFormat.getDateInstance(DateFormat.MEDIUM).format(date) +
                info.replace("""([\w\W]*)(제목 *: *[\w\W]*)(말씀 *: *)""".toRegex(), "\n$3")
    }
}