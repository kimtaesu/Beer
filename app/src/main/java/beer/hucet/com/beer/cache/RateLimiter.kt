package beer.hucet.com.beer.cache

/**
 * Created by taesu on 2017-12-09.
 */
class RateLimiter constructor(
        private val milliSecond: Long) {

    companion object {
        fun now() = System.currentTimeMillis()
    }

    @Synchronized
    fun shouldFetch(upTime: Long): Boolean {
        val now = now()
        if (now - upTime > milliSecond) {
            return true
        }
        return false
    }
}