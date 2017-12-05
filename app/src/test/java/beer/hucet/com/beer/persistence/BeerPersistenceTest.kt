package beer.hucet.com.beer.persistence

import android.arch.persistence.room.Room
import beer.hucet.com.beer.BuildConfig
import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.fixture.BeerFixture
import beer.hucet.com.beer.model.Beer
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.amshove.kluent.`should be`
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.reactivestreams.Subscription
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

/**
 * Created by taesu on 2017-12-05.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class BeerPersistenceTest {

    private lateinit var beersDatabase: BeerDatabase
    private val testBeers = BeerFixture.deserializeBeers("default_punk.json")
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
        val beer = testBeers.first()
        beersDatabase.beerDao().insertBeer(beer)


        beersDatabase.beerDao()
                .getBeerById(beer.id)
                .test()
                .assertComplete()

    }

}