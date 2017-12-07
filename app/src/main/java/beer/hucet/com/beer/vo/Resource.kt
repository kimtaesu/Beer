package beer.hucet.com.beer.vo

import beer.hucet.com.beer.view.paging.LoadState

/**
 * Created by taesu on 2017-12-07.
 */
class Resource<T>(val status: LoadState, val data: T, val message: String) {

//    companion object {
//
//        fun <T> success(data: T?): Resource<T> {
//            return Resource(SUCCESS, data, null)
//        }
//
//        fun <T> error(msg: String, data: T?): Resource<T> {
//            return Resource(ERROR, data, msg)
//        }
//
//        fun <T> loading(data: T?): Resource<T> {
//            return Resource(LOADING, data, null)
//        }
//    }
}
