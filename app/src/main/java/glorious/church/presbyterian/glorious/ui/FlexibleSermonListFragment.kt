package glorious.church.presbyterian.glorious.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.databinding.FragmentMessageListBinding
import glorious.church.presbyterian.glorious.model.Sermon
import glorious.church.presbyterian.glorious.model.Snippet
import glorious.church.presbyterian.glorious.ui.center.CenterMessageListFragment
import glorious.church.presbyterian.glorious.ui.pulpit.PulpitListFragment
import glorious.church.presbyterian.glorious.util.PlayerType
import glorious.church.presbyterian.glorious.util.SharedRef
import kotlinx.android.synthetic.main.fragment_message_list.*
import java.util.*

open class FlexibleSermonListFragment : BaseFragment() {
    protected lateinit var binding: FragmentMessageListBinding

    override var playerType: PlayerType = PlayerType.basic
        set(value) {
            field = value
            shared.playerStyle = value
            binding.playerStyle = value
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_list, container, false)
        shared = SharedRef(this.context)
        playerType = shared.playerStyle
        return binding.root
    }

    protected fun applyWith(sermonList: List<Sermon>, title: (String) -> (String), subInfo: (String, Date) -> (String)) {
        binding.loadingCircularProgressBar.visibility = View.GONE
        sermonListRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        if (sermonList.isNotEmpty()) {
            showBasicSermonInfo(sermonList[0].snippet, title, subInfo)
        }

        sermonListRecyclerView.adapter = SermonsAdapter(sermonList, { sermon ->
            showBasicSermonInfo(sermon.snippet, title, subInfo)
        })
    }

    private fun showBasicSermonInfo(snippet: Snippet, title: (String) -> (String), subInfo: (String, Date) -> (String)) {
        val imageUrl: String
        if (snippet.thumbnails.great != null) {
            imageUrl = snippet.thumbnails.great.url
        } else {
            imageUrl = snippet.thumbnails.high.url
        }

        videoId = snippet.video.id
        dividerView.visibility = View.VISIBLE
        openPlayerButton.visibility = View.VISIBLE

        Picasso.with(this.activity).load(imageUrl).into(mainSermonImageView)
        when(this) {
            is CenterMessageListFragment -> {
                titleTextView.text = title(snippet.title)
            }
            is PulpitListFragment -> {
                titleTextView.text = title(snippet.description)
            }
        }
        subInfoTextView.text = subInfo(snippet.description, snippet.published)
    }

    override fun onDetach() {
        super.onDetach()
        subscriptions.clear()
    }
}