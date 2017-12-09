package beer.hucet.com.beer.cache

import org.amshove.kluent.`should be`
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * Created by taesu on 2017-12-09.
 */
class RateLimiterTest : SubjectSpek<RateLimiter>({
    given("RateLimiter")
    {
        subject { RateLimiter(60 * 1000) }
        on("time1")
        {
            val result = subject.shouldFetch(System.currentTimeMillis() - 60 * 1001)
            it("[true] should fetch ")
            {
                result `should be` true
            }
        }
        on("time2")
        {
            val result = subject.shouldFetch(System.currentTimeMillis())
            it("[false] should fetch ")
            {
                result `should be` false
            }
        }
    }
})