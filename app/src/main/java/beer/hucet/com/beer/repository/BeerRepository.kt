package beer.hucet.com.beer.repository

import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.resolve.RequestResolver
import beer.hucet.com.beer.view.paging.Paging
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(
        private val resolver: RequestResolver) {

    fun getPagingBeers(page: Paging): Single<List<Beer>> {
        return resolver.resolve(page)
    }
}