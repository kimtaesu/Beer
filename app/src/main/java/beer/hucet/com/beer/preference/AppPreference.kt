package beer.hucet.com.beer.preference

import beer.hucet.com.beer.DEFAULT_FRESH_TIME
import beer.hucet.com.beer.DEFAULT_PER_PAGE_SIZE
import com.marcinmoskala.kotlinpreferences.PreferenceHolder

/**
 * Created by taesu on 2017-12-10.
 */
object AppPreference : PreferenceHolder() {

    var freshTime by bindToPreferenceField(DEFAULT_FRESH_TIME)
    var perPageSize by bindToPreferenceField(DEFAULT_PER_PAGE_SIZE)
}