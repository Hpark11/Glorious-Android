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
        title.replace("""([\w\d., ]*)(20[\d]{2})[\W]*(2nd|2nd service|1st|1st service|[dD]istrict|Core|Biz|New *Year's *MSG[\d: ]*|Remnant Day/Core|Biz MSG/[\w ]+)(: *)([\w\W]*)""".toRegex(), "$5")
    }

    val extractedSubInfo: (String, Date) -> (String) = { info, date ->
        val list = info.split(",")
        DateFormat.getDateInstance(DateFormat.MEDIUM).format(date) + "\n${list[0]}\n${list[1].trim()}\n${list[5].trim()}"
    }
}