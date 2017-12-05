package beer.hucet.com.beer.view.adapter

import android.graphics.drawable.Animatable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import beer.hucet.com.beer.R
import beer.hucet.com.beer.glide.GlideRequests
import beer.hucet.com.beer.model.Basic
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.model.Progress

/**
 * Created by taesu on 2017-12-05.
 */
sealed class ViewDelegateAdapter<T : RecyclerView.ViewHolder, R : Basic> {
    abstract fun onBindViewHolder(holder: T, position: Int, item: R, glideRequests: GlideRequests?)

    abstract fun onCreateViewHolder(parent: ViewGroup): T

    class ProggressDelegate : ViewDelegateAdapter<ProgressViewHolder, Progress>() {
        override fun onBindViewHolder(holder: ProgressViewHolder, position: Int, item: Progress, glideRequests: GlideRequests?) {
        }

        override fun onCreateViewHolder(parent: ViewGroup): ProgressViewHolder {
            val v = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.list_item_progress, parent, false)
            return ProgressViewHolder(v)
        }
    }

    class BeerDelegate : ViewDelegateAdapter<BeerViewHolder, Beer>() {
        override fun onBindViewHolder(holder: BeerViewHolder, position: Int, item: Beer, glideRequests: GlideRequests?) {
            holder.name.text = item.name
            holder.desc.text = item.description
            holder.abv.text = "${item.abv}"
            glideRequests?.run {
                load(item.image_url)
                        .into(holder.thumbnail)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup): BeerViewHolder {
            val v = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.list_item_beer, parent, false)
            return BeerViewHolder(v)
        }
    }


}

class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val progress: ImageView = view.findViewById(R.id.progress)
}

class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.name)
    val desc: TextView = view.findViewById(R.id.desc)
    val abv: TextView = view.findViewById(R.id.abv)
    val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
}