package beer.hucet.com.beer.usecase

import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.view.paging.Paging
import io.reactivex.Single

/**
 * Created by taesu on 2017-12-09.
 */
class BeerUseCase constructor(private val beerRepository: BeerRepository) {
    fun getPagingBeer(type: Paging): Single<List<Beer>> =
            beerRepository.getPagingBeers(type)
}