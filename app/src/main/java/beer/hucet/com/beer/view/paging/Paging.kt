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

    fun startIndex(): Int {
        if (page < 1)
            return 0
        return (page - 1) * perPage
    }
}