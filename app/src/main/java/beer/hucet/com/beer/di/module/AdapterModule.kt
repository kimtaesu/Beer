package vingle.hucet.com.vingle.di.module

import beer.hucet.com.beer.view.BeerAdapter
import com.hucet.clean.gallery.inject.scopes.PerActivity
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