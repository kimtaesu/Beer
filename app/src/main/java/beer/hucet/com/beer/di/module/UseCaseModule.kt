package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.usecase.BeerUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-09.
 */
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(repository: BeerRepository): BeerUseCase = BeerUseCase(repository)
}