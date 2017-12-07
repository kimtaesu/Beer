package beer.hucet.com.beer.view.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import beer.hucet.com.beer.OnBeerClickListener
import beer.hucet.com.beer.glide.GlideRequests
import beer.hucet.com.beer.model.Basic
import beer.hucet.com.beer.model.Beer

/**
 * Created by taesu on 2017-12-05.
 */
class BeerAdapter(
        private val delegates: Map<Int, ViewDelegateAdapter<RecyclerView.ViewHolder, Basic>>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val items: ArrayList<Basic> = arrayListOf()

    private var glideRequests: GlideRequests? = null

    private var onBeerClick: OnBeerClickListener? = null
    private var layoutManager: LinearLayoutManager? = null
    fun setGlideRequest(glideRequests: GlideRequests) {
        this.glideRequests = glideRequests
    }

    fun setOnClickListener(layoutManager: LinearLayoutManager, onClick: OnBeerClickListener) {
        this.onBeerClick = onClick
        this.layoutManager = layoutManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val v = delegates[viewType]?.onCreateViewHolder(parent)

        if (v is BeerViewHolder) {
            v?.itemView?.setOnClickListener {
                val position = this.layoutManager?.getPosition(it)!!
                val item = items[position]
                onBeerClick?.invoke(item as Beer)
            }
        }
        return v
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        delegates[item.getViewtype()]?.onBindViewHolder(holder, position, item, glideRequests)
    }

    override fun getItemCount(): Int = items.size

    private fun getLastIndex(): Int = items.lastIndex

    override fun getItemViewType(position: Int): Int = items[position].getViewtype()

    fun update(items: List<Beer>) {
        val start = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(start, this.items.size)
    }

    private fun removeEndProgress() {
        if (this.items.isEmpty()) return

        val lastIndex = getLastIndex()
        val item = this.items[lastIndex]
        if (ItemType.isProgressType(item.getViewtype())) {
            this.items.removeAt(lastIndex)
            notifyItemRemoved(lastIndex)
        }
    }


}