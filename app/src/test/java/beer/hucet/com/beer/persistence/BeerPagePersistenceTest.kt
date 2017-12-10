package beer.hucet.com.beer.persistence

import android.arch.core.executor.testing.InstantTaskExecutorRule
import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.fixture.BeerFixture
import beer.hucet.com.beer.fixture.DBFixture
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by taesu on 2017-12-10.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class BeerPagePersistenceTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var beersDatabase: BeerDatabase
    val testBeers = BeerFixture.deserializeBeers("default_punk.json")
    @Before
    fun initDb() {
        beersDatabase = DBFixture.getDatabase()
    }

    @After
    fun after() {
        beersDatabase.beerDao().deleteAllBeers()
    }

    @Test
    fun pagingLimitQuery() {
        beersDatabase.beerDao().insertAll(testBeers)
        beersDatabase.beerDao().getPagingBeers(0, 5)
                .test()
                .assertComplete()
                .assertValue(testBeers.subList(0, 5))

        beersDatabase.beerDao().getPagingBeers(5, 5)
                .test()
                .assertComplete()
                .assertValue(testBeers.subList(5, 10))


    }
}