package beer.hucet.com.beer.cache

import beer.hucet.com.beer.preference.AppPreference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-09.
 */
@Singleton
class RateLimiter @Inject constructor() {

    @Synchronized
    fun shouldFetch(upTime: Long): Boolean {
        if (upTime > AppPreference.freshTime) {
            return true
        }
        return false
    }


}