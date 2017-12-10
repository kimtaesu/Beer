package beer.hucet.com.beer.fixture

import android.arch.persistence.room.Room
import beer.hucet.com.beer.persistence.BeerDatabase
import org.robolectric.RuntimeEnvironment

/**
 * Created by taesu on 2017-12-10.
 */
object DBFixture {
    fun getDatabase(): BeerDatabase {
        return Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application,
                BeerDatabase::class.java)
                .allowMainThreadQueries()
                .build()
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                        // moving to a new thread
//                        val testBeers = BeerFixture.deserializeBeers("default_punk.json")
//                        getInstance(RuntimeEnvironment.application).beerDao()
//                                .insertAllBeers(testBeers)
//                    }
//                })

    }
}