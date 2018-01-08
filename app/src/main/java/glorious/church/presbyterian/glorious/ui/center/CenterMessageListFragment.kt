package glorious.church.presbyterian.glorious.ui.center

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeStandalonePlayer
import glorious.church.presbyterian.glorious.repository.SermonAPI
import glorious.church.presbyterian.glorious.ui.CustomVideoPlayerActivity
import glorious.church.presbyterian.glorious.ui.FlexibleSermonListFragment
import glorious.church.presbyterian.glorious.util.PlayerType
import glorious.church.presbyterian.glorious.util.obtainViewModel

class CenterMessageListFragment: FlexibleSermonListFragment() {
    private lateinit var viewModel: CenterMessageListViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = obtainViewModel().apply {
            this.setObservables()
            subscriptions.add(this.centerMessages.subscribe({
                Log.d(tag, "${it.toString()}")
                applyWith(sermonList = it.items, title = this.extractedTitle, subInfo = this.extractedSubInfo)
            }))
        }

        binding.openPlayerButton.setOnClickListener {
            val intent: Intent
            if(playerType == PlayerType.youtube) {
                intent = YouTubeStandalonePlayer.createVideoIntent(activity, SermonAPI.key, videoId)
            } else {
                intent = Intent(activity, CustomVideoPlayerActivity::class.java)
                intent.putExtra("videoId", videoId)
            }
            startActivity(intent)
        }
        return binding.root
    }

    private fun obtainViewModel(): CenterMessageListViewModel = obtainViewModel(CenterMessageListViewModel::class.java)

}