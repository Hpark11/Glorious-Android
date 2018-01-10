package glorious.church.presbyterian.glorious.ui.misc

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeStandalonePlayer
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.databinding.FragmentMessageListVerticalBinding
import glorious.church.presbyterian.glorious.model.Sermon
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.ui.BaseFragment
import glorious.church.presbyterian.glorious.ui.CustomVideoPlayerActivity
import glorious.church.presbyterian.glorious.ui.SermonsFixedAdapter
import glorious.church.presbyterian.glorious.util.PlayerType
import glorious.church.presbyterian.glorious.util.SharedRef
import glorious.church.presbyterian.glorious.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_message_list_vertical.*

class MiscMessageListFragment: BaseFragment() {
    private lateinit var binding: FragmentMessageListVerticalBinding
    private lateinit var viewModel: MiscMessageListViewModel

    override var playerType: PlayerType = PlayerType.basic
        set(value) {
            field = value
            shared.playerStyle = value
            binding.playerStyle = value
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_list_vertical, container, false)
        shared = SharedRef(this.context)
        playerType = shared.playerStyle

        viewModel = obtainViewModel().apply {
            this.setObservables()
            subscriptions.add(this.miscMessages.subscribe({
                applyWith(sermonList = it.items, titles = this.titles)
            }))
        }

        return binding.root
    }

    private fun applyWith(sermonList: List<Sermon>, titles: List<String>) {
        binding.loadingCircularProgressBar.visibility = View.GONE
        sermonListVerticalRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        sermonListVerticalRecyclerView.adapter = SermonsFixedAdapter(sermonList, titles, { sermon ->
            val intent: Intent
            if(playerType == PlayerType.youtube) {
                intent = YouTubeStandalonePlayer.createVideoIntent(activity, SermonAPI.key, sermon.id)
            } else {
                intent = Intent(activity, CustomVideoPlayerActivity::class.java)
                intent.putExtra("videoId", sermon.id)
            }
            startActivity(intent)
        })
    }

    private fun obtainViewModel(): MiscMessageListViewModel = obtainViewModel(MiscMessageListViewModel::class.java)
}