package beer.hucet.com.beer.api

import beer.hucet.com.beer.model.Beer
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by taesu on 2017-12-05.
 */
interface PunkApi {
    @GET("beer/{page}/{per_page}")
    fun getPagingBeer(@Path("page") page: Int, @Path("per_page") perPage: Int): Flowable<List<Beer>>
}