package beer.hucet.com.beer.view.paging

/**
 * Created by taesu on 2017-12-08.
 */
class ResourcePage(
        private val state: LoadState,
        private val isLastPage: Boolean) {

    fun isPageAvailable(): Boolean = state != LoadState.LOADING && !isLastPage

    fun getLoadState(): LoadState = state

    override fun equals(other: Any?): Boolean {
        val other = other as? ResourcePage
        other ?: return false
        return other.getLoadState() == state && other.isLastPage == isLastPage
    }
}