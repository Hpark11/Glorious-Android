package glorious.church.presbyterian.glorious.ui.center

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import glorious.church.presbyterian.glorious.model.Result
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.ui.pulpit.PulpitListViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.util.*

class CenterMessageListViewModel(
        context: Application
): AndroidViewModel(context) {
    companion object {
        private val TAG = PulpitListViewModel::class.java.simpleName
    }

    lateinit var centerMessages: Flowable<Result>
    private val apiService = SermonAPI.createAPIService()

    fun setObservables() {
        centerMessages = apiService.sermonsList(SermonAPI.centerMessageListId)
                .flatMap { messages ->
                    Log.d(TAG, messages.toString())
                    Flowable.fromArray(messages)
                }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

//    case centerMessage = "([\\d]{1,2})[,\\.\\ ]*([\\d]{1,2})[,\\.\\ ]*([\\d]{4})[,\\.\\ ]*(2nd|1st|District|Core|Biz)[,\\.\\ /]*([\\w*\\ ]*:\\ *[\\w*\\ ]*:|[\\w*\\ ]*:|:|)\\ *([\\w\\ ?,\\.’'\"!@#$%^&*\\-–]+)([\\w\\ \\-:()]*)"
//    case centerMessageN = "([\\d]{4})[,\\.\\ ]*([\\d]{1,2})[,\\.\\ ]*([\\d]{1,2})[,\\.\\ ]*(biz|g|h|a|b)([\\w\\ \\-:()]*)"
//    case name = "(Sp[\\w]*r)\\ *:\\ *([\\w\\.\\ ]+)"

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