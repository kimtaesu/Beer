package beer.hucet.com.beer.view.paging

import beer.hucet.com.beer.resolve.ResolveType

/**
 * Created by taesu on 2017-12-10.
 */
data class Paging(
        val type: ResolveType,
        val page: Int,
        val perPage: Int
) {
    fun startPosition(): Long {
        if (checkPageZero())
            perPage
        return ((page - 1) * perPage).toLong()
    }

    fun endPosition(): Long {
        if (checkPageZero())
            perPage
        return (page * perPage).toLong()
    }

    fun checkPageZero(): Boolean {
        if (page < 1)
            return true
        return false
    }
}