package beer.hucet.com.beer

import beer.hucet.com.beer.exception.NetworkException
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by taesu on 2017-12-08.
 */
inline fun <T> Single<Response<T>>.networkFailCheck(): Single<Response<T>> {
    return this.map {
        if (!it.isSuccessful) {
            val msg = it.errorBody().use {
                it.string()
            }
            throw NetworkException(msg)
        } else {
            it
        }
    }
}