package beer.hucet.com.beer.repository

import beer.hucet.com.beer.cache.RateLimiter
import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.persistence.BeerDatabase
import beer.hucet.com.beer.view.paging.Paging
import beer.hucet.com.beer.view.paging.UpTimeProvider
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val networkDataSource: NetworkDataSource,
                     private val db: BeerDatabase,
                     private val rateLimit: RateLimiter,
                     private val upTimeProvider: UpTimeProvider) {

    fun getPagingBeers(page: Paging): Single<List<Beer>> {
//        if (rateLimit.shouldFetch(upTimeProvider.resolve(page))) {
        return networkDataSource.getPageBeers(page.page, page.perPage)
//        }
    }
}