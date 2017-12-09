package beer.hucet.com.beer.usecase

import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.repository.BeerRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-09.
 */
@Singleton
class UseCaseImpl @Inject constructor(private val beerRepository: BeerRepository) : FlowableUseCase {
    override fun getPagingBeer(page: Int, perPage: Int): Flowable<List<Beer>> =
            beerRepository.getPagingBeers(page, perPage)

}