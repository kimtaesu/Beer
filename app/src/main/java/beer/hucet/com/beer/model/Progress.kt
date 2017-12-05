package beer.hucet.com.beer.model

import java.util.*

/**
 * Created by taesu on 2017-12-05.
 */
data class Progress(override val id: Long = Long.MAX_VALUE
) : Basic() {
    override val viewType: ViewType
        get() = ViewType.Progress
}