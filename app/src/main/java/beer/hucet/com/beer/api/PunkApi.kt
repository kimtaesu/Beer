package beer.hucet.com.beer.api

import android.arch.lifecycle.LiveData
import beer.hucet.com.beer.model.Beer
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by taesu on 2017-12-05.
 */
interface PunkApi {
    @GET("beers")
    fun getPageBeer(@Query("page") page: Int, @Query("per_page") perPage: Int): Flowable<Response<List<Beer>>>
}