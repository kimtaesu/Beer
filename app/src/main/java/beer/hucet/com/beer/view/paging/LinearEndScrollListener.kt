package beer.hucet.com.beer.view.paging

import android.support.v7.widget.RecyclerView
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.support.v7.widget.LinearLayoutManager


/**
 * Created by taesu on 2017-12-05.
 */
class LinearEndScrollListener(
        private val layoutManager: LinearLayoutManager,
        private val onLoader: () -> Unit
) : RecyclerView.OnScrollListener() {
    private val PAGE_SIZE = 10
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE) {
            onLoader()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}