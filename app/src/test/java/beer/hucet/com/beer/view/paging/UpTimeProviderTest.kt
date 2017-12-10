package beer.hucet.com.beer.view.paging

import beer.hucet.com.beer.resolve.ResolveType
import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.preference.PreferenceHelper
import beer.hucet.com.beer.preference.set
import org.amshove.kluent.`should equal`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by taesu on 2017-12-10.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class UpTimeProviderTest {

    private lateinit var upTimeProviderTest: UpTimeProvider
    private val context = RuntimeEnvironment.application
    @Before
    fun setUp() {
        upTimeProviderTest = UpTimeProvider(context)
    }

    @After
    fun after() {
        PreferenceHelper.defaultPrefs(context).edit().clear().commit()
    }

    @Test
    fun upTimeCreateKey() {
        val page = Paging(ResolveType.Normal(), 1, 1)
        upTimeProviderTest.provide(page)

        val key = upTimeProviderTest.createkeyName(page)

        key `should equal` "${page.type.javaClass.simpleName}_${page.page}_${page.perPage}"
    }

    @Test
    fun getUpTime() {
        val page = Paging(ResolveType.Normal(), 1, 1)
        val key = upTimeProviderTest.createkeyName(page)
        PreferenceHelper.defaultPrefs(RuntimeEnvironment.application)[key] = 10L

        val upTime = upTimeProviderTest.provide(page)
        10L `should equal` upTime
    }

}