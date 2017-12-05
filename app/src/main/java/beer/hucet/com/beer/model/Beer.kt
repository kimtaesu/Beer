package beer.hucet.com.beer.model

/**
 * Created by taesu on 2017-12-05.
 */
data class Beer(
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
) : Basic() {
    override val viewType: ViewType
        get() = ViewType.Beer
}