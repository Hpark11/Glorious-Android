package glorious.church.presbyterian.glorious.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import glorious.church.presbyterian.glorious.ui.BaseFragment
import glorious.church.presbyterian.glorious.ui.FlexibleSermonListFragment

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <T : ViewModel> FlexibleSermonListFragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.activity.application)).get(viewModelClass)

fun <T : ViewModel> BaseFragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.activity.application)).get(viewModelClass)

fun showNetworkAlertDialog(context: Context) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage("네트워크 상태가 좋지 않습니다. 네트워크 확인 후 다시 시도해주세요")
    dialogBuilder.setPositiveButton("확인", null)
    val dialog = dialogBuilder.create()
    dialog.setTitle("네트워크 오류")
    dialog.show()
}