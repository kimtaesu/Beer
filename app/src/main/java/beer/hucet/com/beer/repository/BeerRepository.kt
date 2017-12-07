package beer.hucet.com.beer.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.model.Beer
import retrofit2.Response

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val punkApi: PunkApi) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    fun getPagingBeer(page: Int, perPage: Int): LiveData<Response<List<Beer>>> {
        return punkApi.getPagingBeer(page, perPage)
    }

    private fun fetchFromNetwork() {

    }
}