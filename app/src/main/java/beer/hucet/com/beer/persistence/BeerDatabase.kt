package beer.hucet.com.beer.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import beer.hucet.com.beer.model.Beer

/**
 * Created by taesu on 2017-12-05.
 */
@Database(entities = arrayOf(Beer::class), version = 1)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao

    companion object {

        @Volatile private var INSTANCE: BeerDatabase? = null

        fun getInstance(context: Context): BeerDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        BeerDatabase::class.java, "beers.db")
                        .build()
    }

}