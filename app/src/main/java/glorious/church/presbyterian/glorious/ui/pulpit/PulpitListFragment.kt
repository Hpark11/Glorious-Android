package glorious.church.presbyterian.glorious.ui.pulpit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.youtube.player.YouTubeStandalonePlayer
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.ui.FlexibleSermonListFragment
import glorious.church.presbyterian.glorious.ui.CustomVideoPlayerActivity
import glorious.church.presbyterian.glorious.util.PlayerType
import glorious.church.presbyterian.glorious.util.obtainViewModel

class PulpitListFragment: FlexibleSermonListFragment() {
    private lateinit var viewModel: PulpitListViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = obtainViewModel().apply {
            this.setObservables()
            subscriptions.add(this.pulpitMessages.subscribe({
                applyWith(sermonList = it.items, title = this.extractedTitle, subInfo = this.extractedSubInfo)
            }))
        }

        binding.openPlayerButton.setOnClickListener {
            val intent: Intent
            if(playerType == PlayerType.youtube) {
                intent = YouTubeStandalonePlayer.createVideoIntent(this.activity, SermonAPI.key, videoId)
            } else {
                intent = Intent(this.activity, CustomVideoPlayerActivity::class.java)
                intent.putExtra("videoId", videoId)
            }
            startActivity(intent)
        }
        return binding.root
    }

    private fun obtainViewModel(): PulpitListViewModel = obtainViewModel(PulpitListViewModel::class.java)
}