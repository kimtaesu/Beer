package beer.hucet.com.beer

import beer.hucet.com.beer.exception.NetworkException
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Publisher
import retrofit2.Response

/**
 * Created by taesu on 2017-12-08.
 */
inline fun <T> Flowable<Response<T>>.networkFailCheck(): Flowable<Response<T>> {
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