package beer.hucet.com.beer.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-07.
 */
@Singleton
class BeerViewModel @Inject constructor(
        private val repository: BeerRepository,
        private val schedulerProvider: DefaultSchedulerProvider)
    : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val beers = MutableLiveData<List<Beer>>()
    private
    fun requestFetch() {
        repository.getPagingBeer(1, 30)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    beers.postValue(it)
                })
                .let { compositeDisposable.add(it) }
    }

    fun getBeersLivData(): MutableLiveData<List<Beer>> = beers

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}