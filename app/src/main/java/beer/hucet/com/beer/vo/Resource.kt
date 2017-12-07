package beer.hucet.com.beer.vo

import beer.hucet.com.beer.viewmodel.LoadingState

/**
 * Created by taesu on 2017-12-07.
 */
class Resource<T>(val status: LoadingState, val data: T?, val message: String?) {


    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
