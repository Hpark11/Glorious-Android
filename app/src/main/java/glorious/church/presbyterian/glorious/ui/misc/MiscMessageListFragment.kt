package glorious.church.presbyterian.glorious.ui.misc

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.ui.BaseFragment
import glorious.church.presbyterian.glorious.databinding.FragmentFeaturedBinding

class MiscMessageListFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}