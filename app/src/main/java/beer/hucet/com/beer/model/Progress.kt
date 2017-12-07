package beer.hucet.com.beer.model

import beer.hucet.com.beer.view.adapter.ItemType

/**
 * Created by taesu on 2017-12-05.
 */
class Progress(
        override val id: Long = Long.MAX_VALUE
) : Basic() {
    override fun getViewtype(): Int = ItemType.Progress.type
}