package glorious.church.presbyterian.glorious.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import glorious.church.presbyterian.glorious.databinding.ItemSermonFixedBinding
import glorious.church.presbyterian.glorious.model.Sermon


class SermonsFixedAdapter(
        val items: List<Sermon>,
        val listener: (Sermon) -> Unit
): RecyclerView.Adapter<SermonsFixedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            = ViewHolder(ItemSermonFixedBinding.inflate(LayoutInflater.from(parent!!.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int)
            = holder!!.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(val sermonBinding: ItemSermonFixedBinding): RecyclerView.ViewHolder(sermonBinding.root) {
        fun bind(item: Sermon, listener: (Sermon) -> Unit) = with(itemView) {
            sermonBinding.sermon = item
            sermonBinding.sermonFixedCardView.setOnClickListener { listener(item) }
        }
    }
}