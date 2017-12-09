package beer.hucet.com.beer.preference

import beer.hucet.com.beer.TestApplication
import org.amshove.kluent.`should be`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by taesu on 2017-12-09.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class PreferenceWrapperTest {

    private lateinit var preferenceWrapper: PreferenceWrapper

    @Before
    fun setUp() {
        preferenceWrapper = PreferenceWrapper(RuntimeEnvironment.application)
    }

    @After
    fun after() {
        PreferenceHelper.defaultPrefs(RuntimeEnvironment.application).edit().clear().commit()
    }

    @Test
    fun cacheTimePutOrGet() {
        val testTime = { 123L }
        val time = preferenceWrapper.getCachedTime("test", testTime)

        time `should be` testTime()
    }
}