package beer.hucet.com.beer.presenter

import android.arch.lifecycle.LifecycleObserver

/**
 * Created by taesu on 2017-12-05.
 */
interface BeerRequest {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError()
    }

    interface Presenter : LifecycleObserver {
        fun getBeer(page: Int, perPage: Int)
    }
}