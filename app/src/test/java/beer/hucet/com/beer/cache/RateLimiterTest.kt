package beer.hucet.com.beer.cache

import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.preference.AppPreference
import org.amshove.kluent.`should be`
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by taesu on 2017-12-09.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class RateLimiterTest {
    private lateinit var rateLimit: RateLimiter
    @Before
    fun setUp() {
        AppPreference.freshTime = 10
        rateLimit = RateLimiter()
    }

    @After
    fun after() {
        AppPreference.clear()
    }

    @Test
    fun expiredFetchTime() {
        val result = rateLimit.shouldFetch(20)
        result `should be` true
    }

    @Test
    fun withinFetchTime() {
        val result = rateLimit.shouldFetch(0)
        result `should be` false
    }
}