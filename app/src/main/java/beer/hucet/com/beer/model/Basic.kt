package beer.hucet.com.beer.model

/**
 * Created by taesu on 2017-12-05.
 */

enum class ViewType(val type: Int) {
    Beer(0), Progress(1);

    companion object {
        fun getType(type: Int): ViewType =
                when (type) {
                    Beer.type -> {
                        Beer
                    }
                    Progress.type -> {
                        Progress
                    }
                    else -> {
                        throw IllegalArgumentException("$type is unknown")
                    }
                }
    }
}

abstract class Basic() {
    abstract val id: Long
    abstract val viewType: ViewType
}