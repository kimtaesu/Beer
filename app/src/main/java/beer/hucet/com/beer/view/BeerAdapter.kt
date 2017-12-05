package beer.hucet.com.beer.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import beer.hucet.com.beer.R
import beer.hucet.com.beer.model.Beer

/**
 * Created by taesu on 2017-12-05.
 */
class BeerAdapter : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {
    private val items: ArrayList<Beer> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerAdapter.BeerViewHolder {
        val v = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_beer, parent, false)
        return BeerViewHolder(v)
    }

    override fun onBindViewHolder(holder: BeerAdapter.BeerViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.desc.text = item.description
    }

    override fun getItemCount(): Int = items.size

    inner class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val desc: TextView = view.findViewById(R.id.desc)
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    }
}