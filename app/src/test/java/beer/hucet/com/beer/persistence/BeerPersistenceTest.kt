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
class BeerPersistenceTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var beersDatabase: BeerDatabase

    @Before
    fun initDb() {
        beersDatabase = DBFixture.getDatabase()
    }

    @After
    fun stopService() {
        beersDatabase.beerDao().deleteAllBeers()
    }

    @Test
    fun insertBeerSavesData() {
        val testBeers = BeerFixture.deserializeBeers("default_punk.json")
        val beer = testBeers.first()
        beersDatabase.beerDao().insert(beer)

        beersDatabase.beerDao()
                .getBeerById(beer.id)
                .test()
                .assertComplete()
                .assertValue {
                    it.id == beer.id
                }
    }

    @Test
    fun insertAllBeersSavesData() {
        val testBeers = BeerFixture.deserializeBeers("default_punk.json")
        beersDatabase.beerDao().insertAll(testBeers)

        beersDatabase.beerDao()
                .getAllBeers()
                .test()
                .assertComplete()
                .assertValue(testBeers)
    }

    @Test
    fun aaa() {
        val testBeers = BeerFixture.deserializeBeers("default_punk.json")
        beersDatabase.beerDao().insertAll(testBeers)
//        val normals = testBeers.map {
//            Normal(ownerId = it.id)
//        }
//        beersDatabase.normalDao().insertAllNormal(normals)
    }
}