package beer.hucet.com.beer.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import beer.hucet.com.beer.view.adapter.ItemType
import java.io.Serializable

/**
 * Created by taesu on 2017-12-05.
 */
@Entity(tableName = "beers")
data class Beer(
        @PrimaryKey
        @ColumnInfo(name = "beer_id")
        override val id: Long,
        val name: String,
        val first_brewed: String,
        val description: String,
        val image_url: String,
        val abv: Float,
        val ibu: Float?,
        val ebc: Float?,
        val srm: Float?,
        val ph: Float?
) : Basic(), Serializable {
    @Ignore
    override fun getViewtype(): Int = ItemType.Beer.type
}