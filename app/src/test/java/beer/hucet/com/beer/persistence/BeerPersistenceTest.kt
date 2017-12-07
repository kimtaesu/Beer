package beer.hucet.com.beer.persistence

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.fixture.BeerFixture
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by taesu on 2017-12-05.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class BeerPersistenceTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var beersDatabase: BeerDatabase

    @Before
    fun initDb() {
        beersDatabase = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application,
                BeerDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @Test
    fun insertBeerSavesData() {
        val testBeers = BeerFixture.deserializeBeers("default_punk.json")
        val beer = testBeers.first()
        beersDatabase.beerDao().insertBeer(beer)

        beersDatabase.beerDao()
                .getBeerById(beer.id)
                .test()
                .assertComplete()
                .assertValue {
                    it.id == beer.id
                }
    }
}