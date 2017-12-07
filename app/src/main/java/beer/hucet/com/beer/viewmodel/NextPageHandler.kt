package beer.hucet.com.beer.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * Created by taesu on 2017-12-07.
 */
class NextPageHandler : Observer<Boolean> {
    private val loadMoreState = MutableLiveData<LoadingState>()

    fun requestNextPage() {
        loadMoreState.value = LoadingState.Loading
    }

    override fun onChanged(t: Boolean?) {

    }
}