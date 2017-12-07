package beer.hucet.com.beer.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.view.paging.LinearEndScrollListener
import beer.hucet.com.beer.view.paging.LoadState
import beer.hucet.com.beer.view.paging.ResourcePage
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-07.
 */
@Singleton
class BeerViewModel @Inject constructor(
        repository: BeerRepository,
        schedulerProvider: DefaultSchedulerProvider)
    : ViewModel() {

    private val nextPage = NextPageHandler(repository, schedulerProvider)
    private val compositeDisposable = CompositeDisposable()
    private val beers = MutableLiveData<List<Beer>>()
    fun requestFetch() {
        nextPage.nextPage()
    }

    fun getBeersLivData(): MutableLiveData<List<Beer>> = beers
    fun getLoadMoreLiveData() = nextPage.getLoadMore()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    inner class NextPageHandler(
            private val repository: BeerRepository,
            private val schedulerProvider: DefaultSchedulerProvider
    ) {
        private var curPage = AtomicInteger()
        private val loadMoreState = MutableLiveData<ResourcePage>()
        fun nextPage() {
            if (loadMoreState.value?.isPageAvailable() == false)
                return
            loadMoreState.postValue(ResourcePage(LoadState.LOADING, false))

            repository.getPagingBeer(curPage.incrementAndGet(), LinearEndScrollListener.PAGE_SIZE)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        beers.postValue(it)
                        loadMoreState.postValue(ResourcePage(LoadState.SUCCESS, it.isEmpty()))
                    }, {
                        loadMoreState.postValue(ResourcePage(LoadState.ERROR, false))
                    })
                    .let { compositeDisposable.add(it) }
        }

        fun getLoadMore() = loadMoreState
    }
}