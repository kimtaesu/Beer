package vingle.hucet.com.vingle.di.module

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.repository.BeerRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by taesu on 2017-11-10.
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideGithubRepository(punkApi: PunkApi): BeerRepository {
        return BeerRepository(punkApi)
    }
}