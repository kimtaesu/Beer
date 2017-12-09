package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.persistence.BeerDatabase
import beer.hucet.com.beer.preference.PreferenceWrapper
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
    fun provideNetworkDataSource(api: PunkApi): NetworkDataSource = NetworkDataSource(api)

    @Provides
    @Singleton
    fun provideBeerRepository(netowrk: NetworkDataSource, beerDatabase: BeerDatabase, pref: PreferenceWrapper): BeerRepository =
            BeerRepository(netowrk, beerDatabase, pref)
}