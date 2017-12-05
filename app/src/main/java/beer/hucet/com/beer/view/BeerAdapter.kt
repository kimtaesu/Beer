package beer.hucet.com.beer.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import beer.hucet.com.beer.model.Beer

/**
 * Created by taesu on 2017-12-05.
 */
class BeerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<Beer> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = items.size

}