package beer.hucet.com.beer.usecase

import beer.hucet.com.beer.model.Beer
import io.reactivex.Flowable

/**
 * Created by taesu on 2017-12-09.
 */
interface FlowableUseCase {
    fun getPagingBeer(page: Int, perPage: Int): Flowable<List<Beer>>
}