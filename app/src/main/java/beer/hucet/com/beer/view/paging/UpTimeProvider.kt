package beer.hucet.com.beer.view.paging

import android.app.Application
import beer.hucet.com.beer.preference.PreferenceHelper
import beer.hucet.com.beer.preference.get
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-10.
 */
@Singleton
class UpTimeProvider @Inject constructor(private val application: Application) {

    fun resolve(page: Paging): Long {
        return getUpTime(resolveKeyName(page))
    }

    fun resolveKeyName(page: Paging): String {
        return "${page.type.javaClass.simpleName}_${page.page}_${page.perPage}"
    }

    private fun getUpTime(key: String): Long {
        val sharedPref = PreferenceHelper.defaultPrefs(application)
        val now: Long = System.currentTimeMillis()
        return sharedPref[key, now]!!
    }
}