package beer.hucet.com.beer.view.paging

import beer.hucet.com.beer.viewmodel.LoadingState

/**
 * Created by taesu on 2017-12-05.
 */
open class PagingAvailability(private val loadingChecker: LoadingChecker) {

    private var isLoading = false

    init {
        loadingChecker
                .subscribe({
                    when (it) {
                        LoadingState.Loading -> {
                            isLoading = false
                        }
                        LoadingState.Complete -> {
                            isLoading = true
                        }
                    }
                })
    }

    fun availablePaging(): Boolean = isLoading
}