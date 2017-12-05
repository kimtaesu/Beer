package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.view.BeerAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by taesu on 2017-12-04.
 */
@Module
class AdapterModule {

    @PerActivity
    @Provides
    fun provideAdapter(): BeerAdapter = BeerAdapter()
}