package beer.hucet.com.beer.repository

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.model.Beer
import io.reactivex.Flowable

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val punkApi: PunkApi) {
    fun getPagingBeer(page: Int, perPage: Int): Flowable<List<Beer>> {
        return punkApi.getPagingBeer(page, perPage)
    }
}