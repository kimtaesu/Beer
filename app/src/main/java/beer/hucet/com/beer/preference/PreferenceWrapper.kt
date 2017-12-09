package beer.hucet.com.beer.preference

import android.app.Application
import android.support.annotation.VisibleForTesting
import beer.hucet.com.beer.DEFAULT_FRESH_TIME
import beer.hucet.com.beer.DEFAULT_PER_PAGE_SIZE
import beer.hucet.com.beer.KEY_FRESH_TIME
import beer.hucet.com.beer.KEY_PER_PAGE_SIZE
import beer.hucet.com.beer.cache.RateLimiter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-09.
 */
@Singleton
class PreferenceWrapper @Inject constructor(
        private val application: Application) {

    fun getCachedTime(key: String, getTime: () -> Long = ::getNowTime): Long {
        val sharedPref = PreferenceHelper.defaultPrefs(application)
        val upTime = {
            val t: Long? = sharedPref[key]
            if (t == -1L) {
                val now = getTime()
                sharedPref[key] = now
                now
            } else {
                t!!
            }
        }
        return upTime()
    }

    fun getFreshMilliSecond(): Long {
        val sharedPref = PreferenceHelper.defaultPrefs(application)
        return sharedPref[KEY_FRESH_TIME, DEFAULT_FRESH_TIME]!!
    }

    fun getPerPageSize(): Int {
        val sharedPref = PreferenceHelper.defaultPrefs(application)
        return sharedPref[KEY_PER_PAGE_SIZE, DEFAULT_PER_PAGE_SIZE]!!
    }
}

@VisibleForTesting
private fun getNowTime(): Long {
    return RateLimiter.now()
}
