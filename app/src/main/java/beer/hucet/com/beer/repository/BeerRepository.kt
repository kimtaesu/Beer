package beer.hucet.com.beer.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.model.Beer
import io.reactivex.Flowable
import retrofit2.Response

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepository(private val punkApi: PunkApi) {


    fun getPagingBeer(page: Int, perPage: Int): Flowable<List<Beer>> =
            punkApi.getPagingBeer(page, perPage)

}