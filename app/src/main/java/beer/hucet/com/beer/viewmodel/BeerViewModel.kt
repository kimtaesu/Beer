package beer.hucet.com.beer.viewmodel

import android.arch.lifecycle.ViewModel
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.scheduler.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-07.
 */
@Singleton
class BeerViewModel @Inject constructor(
        private val repository: BeerRepository,
        private val schedulerProvider: SchedulerProvider = DefaultSchedulerProvider())
    : ViewModel() {

    fun requestFetch() {

    }

    override fun onCleared() {
        super.onCleared()
    }

}