package beer.hucet.com.beer.repository

import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.model.Beer
import io.reactivex.Flowable


/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val networkDataSource: NetworkDataSource) {


    fun getPagingBeers(page: Int, perPage: Int): Flowable<List<Beer>> {
        return networkDataSource.getPageBeers(page, perPage)
    }

}