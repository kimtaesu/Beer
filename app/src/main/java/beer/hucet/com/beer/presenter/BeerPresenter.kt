package beer.hucet.com.beer.presenter

import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.view.BeerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by taesu on 2017-12-05.
 */
class BeerPresenter(
        private val view: BeerRequest.View,
        private val repository: BeerRepository,
        private val adapter: BeerAdapter) : BeerRequest.Presenter {
    override fun getBeer(page: Int, perPage: Int) {
        repository.getPagingBeer(page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Timber.d("doOnSubscribe");
                    view.showProgress()
                }
                .doOnTerminate {
                    Timber.d("doOnTerminate")
                    view.hideProgress()
                }
                .subscribe({
                    Timber.d("subscribe ${it}")
                }, {
                    Timber.d("error ${it}")
                    view.showError()
                }, {
                    Timber.d("Complete")
                })
    }
}