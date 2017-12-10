package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.persistence.BeerDatabase
import beer.hucet.com.beer.resolve.RequestResolver
import beer.hucet.com.beer.view.paging.RateLimiter
import beer.hucet.com.beer.view.paging.UpTimeProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-10.
 */

@Module
class ResolveModule {
    @Provides
    @Singleton
    fun provideRequestResolver(n: NetworkDataSource, db: BeerDatabase, r: RateLimiter, u: UpTimeProvider): RequestResolver {
        return RequestResolver(n, db, r, u)
    }
}