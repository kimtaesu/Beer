package beer.hucet.com.beer.view.paging

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import beer.hucet.com.beer.preference.AppPreference


/**
 * Created by taesu on 2017-12-05.
 */
class LinearEndScrollListener(
        private val layoutManager: LinearLayoutManager,
        private val onLoader: () -> Unit

) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= AppPreference.perPageSize) {
            onLoader()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}