package beer.hucet.com.beer.repository

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.exception.NetworkException
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.networkFailCheck
import io.reactivex.Flowable
import retrofit2.Response

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val punkApi: PunkApi) {
    fun getPagingBeer(page: Int, perPage: Int): Flowable<List<Beer>> =
            punkApi.getPagingBeer(page, perPage)
                    .networkFailCheck()
                    .map {
                        if (it.body() == null) emptyList()
                        else it.body()
                    }
}