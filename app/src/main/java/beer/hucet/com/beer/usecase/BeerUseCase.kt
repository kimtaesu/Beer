package beer.hucet.com.beer.usecase

import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.repository.BeerRepository
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-09.
 */
class BeerUseCase constructor(private val beerRepository: BeerRepository) {
    fun getPagingBeer(page: Int, perPage: Int): Single<List<Beer>> =
            beerRepository.getPagingBeers(page, perPage)
}