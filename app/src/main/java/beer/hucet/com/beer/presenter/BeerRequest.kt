package beer.hucet.com.beer.presenter

import android.arch.lifecycle.LifecycleObserver
import beer.hucet.com.beer.model.Beer

/**
 * Created by taesu on 2017-12-05.
 */
interface BeerRequest {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError()
        fun update(items: List<Beer>)
    }

    interface Presenter : LifecycleObserver {
        fun requestFetch(page: Int, perPage: Int)
        fun cancelFetch()
    }
}