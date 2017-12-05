package beer.hucet.com.beer.presenter

import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.scheduler.SchedulerProvider
import beer.hucet.com.beer.view.adapter.BeerAdapter
import timber.log.Timber

/**
 * Created by taesu on 2017-12-05.
 */
class BeerPresenter(
        private val views: Set<BeerRequest.View>,
        private val repository: BeerRepository,
        private val adapter: BeerAdapter,
        private val schedulerProvider: SchedulerProvider = DefaultSchedulerProvider()
) : BeerRequest.Presenter {
    override fun getBeer(page: Int, perPage: Int) {
        repository.getPagingBeer(page, perPage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .doOnSubscribe {
                    Timber.d("doOnSubscribe");
                    views.forEach {
                        it.showProgress()
                    }
                }
                .doOnTerminate {
                    Timber.d("doOnTerminate")
                    views.forEach {
                        it.hideProgress()
                    }
                }
                .subscribe({
                    Timber.d("subscribe ${it}")
                    adapter.update(it)
                }, {
                    Timber.e("error ${it}")
                    views.forEach {
                        it.showError()
                    }
                }, {
                    Timber.d("Complete")
                })
    }
}