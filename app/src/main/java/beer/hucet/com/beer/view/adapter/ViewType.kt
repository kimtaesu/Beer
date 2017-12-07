package beer.hucet.com.beer.view.adapter

/**
 * Created by taesu on 2017-12-07.
 */
interface ViewType {
    fun getViewtype(): Int
}

enum class ItemType(val type: Int) {
    Beer(0), Progress(1);

    companion object {
        fun isBeerType(type: Int): Boolean = type == Beer.type
        fun isProgressType(type: Int): Boolean = type == Progress.type
    }
}
