package beer.hucet.com.beer.repository

import beer.hucet.com.beer.KEY_PAGE_PREFIX
import beer.hucet.com.beer.cache.RateLimiter
import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.persistence.BeerDatabase
import beer.hucet.com.beer.preference.PreferenceWrapper
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val networkDataSource: NetworkDataSource,
                     private val db: BeerDatabase,
                     private val preferenceWrapper: PreferenceWrapper) {

    private val rateLimit = RateLimiter(preferenceWrapper.getFreshMilliSecond())

    fun getPagingBeers(page: Int, perPage: Int): Single<List<Beer>> {
        val upTime = preferenceWrapper.getCachedTime("${KEY_PAGE_PREFIX}${page * perPage}")
        if (rateLimit.shouldFetch(upTime)) {
            networkDataSource.getPageBeers(page, perPage)
        }
        return networkDataSource.getPageBeers(page, perPage)
    }

}