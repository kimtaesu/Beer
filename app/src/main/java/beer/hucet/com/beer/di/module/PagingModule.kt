package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.view.paging.LoadingChecker
import beer.hucet.com.beer.view.paging.PagingAvailability
import dagger.Module
import dagger.Provides

/**
 * Created by taesu on 2017-12-04.
 */
@Module
class PagingModule {
    @Provides
    @PerActivity
    fun provideLoadingChecker(): LoadingChecker = LoadingChecker()

    @Provides
    @PerActivity
    fun providePager(loadingChecker: LoadingChecker): PagingAvailability = PagingAvailability(loadingChecker)
}