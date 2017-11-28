package glorious.church.presbyterian.glorious.controller.center

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.controller.BaseFragment
import glorious.church.presbyterian.glorious.databinding.FragmentMessageListBinding


/**
 * Created by hpark_ipl on 2017. 11. 27..
 */

class CenterMessageListFragment: BaseFragment() {

    private lateinit var b: FragmentMessageListBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_message_list, container, false)

        return b.root
    }

}