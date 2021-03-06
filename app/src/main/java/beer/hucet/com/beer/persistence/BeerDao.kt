package beer.hucet.com.beer.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import beer.hucet.com.beer.model.Beer
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-05.
 */
@Dao
interface BeerDao : BaseDao<Beer> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Beer>)

    @Query("DELETE FROM beers")
    fun deleteAllBeers()

    @Query("SELECT * FROM beers WHERE beer_id = :id")
    fun getBeerById(id: Long): Single<Beer>

    @Query("SELECT * FROM beers")
    fun getAllBeers(): Single<List<Beer>>

    @Query("SELECT * FROM beers LIMIT :startIndex, :perPage")
    fun getPagingBeers(startIndex: Int, perPage: Int): Single<List<Beer>>

}