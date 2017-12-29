package glorious.church.presbyterian.glorious.controller.pulpit

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.controller.BaseFragment
import glorious.church.presbyterian.glorious.databinding.FragmentMessageListBinding
import glorious.church.presbyterian.glorious.model.Sermon
import glorious.church.presbyterian.glorious.util.ViewModelFactory
import glorious.church.presbyterian.glorious.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_message_list.view.*

class PulpitListFragment: BaseFragment() {

    private lateinit var b: FragmentMessageListBinding
    private lateinit var viewModel: PulpitListViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_message_list, container, false)
        initViews(b.root)

        //ViewModelProviders.of(this).

        viewModel = obtainViewModel().apply {
            this.setObservables()
            subscriptions.add(this.pulpitMessages.subscribe({
                Log.d(tag, "${it.toString()}")
                applyWith(sermonList = it.items)
            }))
        }

        return b.root
    }

    private fun initViews(v: View) {

    }

    private fun applyWith(sermonList: List<Sermon>) {

    }

    override fun onDetach() {
        super.onDetach()

    }

    private fun obtainViewModel(): PulpitListViewModel = obtainViewModel(PulpitListViewModel::class.java)
}