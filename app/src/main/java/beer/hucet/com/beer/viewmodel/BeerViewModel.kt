package beer.hucet.com.beer.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import beer.hucet.com.beer.resolve.ResolveType
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.preference.AppPreference
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.scheduler.SchedulerProvider
import beer.hucet.com.beer.usecase.BeerUseCase
import beer.hucet.com.beer.view.paging.LoadState
import beer.hucet.com.beer.view.paging.Paging
import beer.hucet.com.beer.view.paging.ResourcePage
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-07.
 */
@Singleton
class BeerViewModel @Inject constructor(
        useCase: BeerUseCase,
        schedulerProvider: SchedulerProvider = DefaultSchedulerProvider())
    : ViewModel() {

    private val nextPage = NextPageHandler(useCase, schedulerProvider)
    private val compositeDisposable = CompositeDisposable()
    private val beers = MutableLiveData<List<Beer>>()
    private val error = MutableLiveData<String>()
    fun requestFetch(type : ResolveType) {
        nextPage.nextPage(type)
    }

    fun getBeersLivData(): MutableLiveData<List<Beer>> = beers
    fun getLoadMoreLiveData() = nextPage.getLoadMore()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getErrorLiveData() = error

    inner class NextPageHandler(
            private val useCase: BeerUseCase,
            private val schedulerProvider: SchedulerProvider
    ) {

        private var curPage = AtomicInteger()
        private val loadMoreState = MutableLiveData<ResourcePage>()
        fun nextPage(type: ResolveType) {
            if (loadMoreState.value?.isPageAvailable() == false)
                return
            loadMoreState.postValue(ResourcePage(LoadState.LOADING, false))
            useCase.getPagingBeer(Paging(type, curPage.incrementAndGet(), AppPreference.perPageSize))
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        beers.postValue(it)
                        loadMoreState.postValue(ResourcePage(LoadState.SUCCESS, it.isEmpty()))
                    }, { e ->
                        loadMoreState.postValue(ResourcePage(LoadState.ERROR, false))
                        error.postValue(e.message)
                    })
                    .let { compositeDisposable.add(it) }
        }

        fun getLoadMore() = loadMoreState
    }
}