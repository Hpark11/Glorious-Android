package glorious.church.presbyterian.glorious.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import glorious.church.presbyterian.glorious.model.Sermon

class SermonsAdapter(
        val items: List<Sermon>,
        val listener: (Sermon) -> Unit
): RecyclerView.Adapter<SermonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) = holder!!.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(items: Sermon, listener: (Sermon) -> Unit) = with(itemView) {
        }
    }
}