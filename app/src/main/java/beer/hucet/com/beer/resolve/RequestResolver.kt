package beer.hucet.com.beer.resolve

import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.persistence.BeerDatabase
import beer.hucet.com.beer.view.paging.Paging
import beer.hucet.com.beer.view.paging.RateLimiter
import beer.hucet.com.beer.view.paging.UpTimeProvider
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-10.
 */
class RequestResolver(
        private val networkDataSource: NetworkDataSource,
        private val db: BeerDatabase,
        private val rateLimit: RateLimiter,
        private val upTimeProvider: UpTimeProvider
) {
    fun resolve(page: Paging): Single<List<Beer>> {
        return when (page.type) {
            is ResolveType.Normal -> {
                if (rateLimit.shouldFetch(upTimeProvider.provide(page)))
                    networkDataSource.getPageBeers(page.page, page.perPage)
                            .map {
                                db.beerDao().insertAll(it)
                                it
                            }
                else
                    db.beerDao().getPagingBeers(page.startPosition(), page.endPosition())
            }
        }
    }
}