package beer.hucet.com.beer.presenter

import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.view.BeerAdapter

/**
 * Created by taesu on 2017-12-05.
 */
class BeerPresenter(
        private val view: BeerRequest.View,
        private val repository: BeerRepository,
        private val adapter: BeerAdapter) : BeerRequest.Presenter {
    override fun getBeer(page: Int, perPage: Int) {
    }
}