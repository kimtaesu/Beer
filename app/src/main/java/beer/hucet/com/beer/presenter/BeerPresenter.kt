package beer.hucet.com.beer.presenter

import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by taesu on 2017-12-05.
 */
class BeerPresenter(
        private val views: Set<BeerRequest.View>,
        private val repository: BeerRepository,
        private val schedulerProvider: SchedulerProvider = DefaultSchedulerProvider()
) : BeerRequest.Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun requestFetch(page: Int, perPage: Int) {
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
                .subscribe({ items ->
                    Timber.d("subscribe ${items}")
                    views.forEach {
                        it.update(items)
                    }
                }, {
                    Timber.e("error ${it}")
                    views.forEach {
                        it.showError()
                    }
                }, {
                    Timber.d("Complete")
                })
                .let {
                    compositeDisposable.add(it)
                }
    }

    override fun cancelFetch() {
        compositeDisposable.clear()
    }
}