package glorious.church.presbyterian.glorious.ui.pulpit

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeStandalonePlayer
import glorious.church.presbyterian.glorious.R
import glorious.church.presbyterian.glorious.ui.BaseFragment
import glorious.church.presbyterian.glorious.databinding.FragmentMessageListBinding
import glorious.church.presbyterian.glorious.model.Sermon
import glorious.church.presbyterian.glorious.util.SermonAPI
import glorious.church.presbyterian.glorious.util.obtainViewModel

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

//        b.youtubePlayerView.initialize(SermonAPI.key, this)
//        youtubePlayerView.initialize(SermonAPI.key, YouTubePlayer.OnInitializedListener(
//        ))

        val intent: Intent = YouTubeStandalonePlayer.createVideoIntent(this.activity, SermonAPI.key, "1kOKRsfo1LI")
        startActivity(intent)
        return b.root
    }

    private fun initViews(v: View) {

    }

    private fun applyWith(sermonList: List<Sermon>) {

    }

    private fun obtainViewModel(): PulpitListViewModel = obtainViewModel(PulpitListViewModel::class.java)
}