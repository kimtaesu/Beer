package beer.hucet.com.beer.view.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import beer.hucet.com.beer.OnBeerClickListener
import beer.hucet.com.beer.glide.GlideRequests
import beer.hucet.com.beer.model.Basic
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.model.Progress
import beer.hucet.com.beer.model.ViewType

/**
 * Created by taesu on 2017-12-05.
 */
class BeerAdapter(
        private val delegates: Map<ViewType, ViewDelegateAdapter<RecyclerView.ViewHolder, Basic>>) :
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
        val v = delegates[ViewType.getType(viewType)]?.onCreateViewHolder(parent)

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
        delegates[item.viewType]?.onBindViewHolder(holder, position, item, glideRequests)
    }

    override fun getItemCount(): Int = items.size

    private fun getLastIndex(): Int = items.lastIndex

    override fun getItemViewType(position: Int): Int = items[position].viewType.type

    fun update(items: List<Beer>) {
        removeEndProgress()

        val start = itemCount
        this.items.addAll(items)
        addEndProgress()
        notifyItemRangeInserted(start, this.items.size)
    }

    private fun removeEndProgress() {
        if (this.items.isEmpty()) return

        val lastIndex = getLastIndex()
        val item = this.items[lastIndex]
        if (item is Progress) {
            this.items.removeAt(lastIndex)
            notifyItemRemoved(lastIndex)
        }
    }

    private fun addEndProgress() {
        this.items.add(Progress())
        notifyItemInserted(getLastIndex())
    }


}