package beer.hucet.com.beer.datasource

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.networkFailCheck
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-09.
 */
class NetworkDataSource(private val api: PunkApi) {
    fun getPageBeers(page: Int, perPage: Int): Single<List<Beer>> {
        return api.getPageBeer(page, perPage)
                .networkFailCheck()
                .map {
                    if (it.body() == null) emptyList()
                    else it.body()
                }
    }
}