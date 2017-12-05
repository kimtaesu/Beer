package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.presenter.BeerRequest
import beer.hucet.com.beer.view.MainActivity
import beer.hucet.com.beer.view.paging.LoadingChecker
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by taesu on 2017-10-30.
 */
@Module
abstract class MainAcitivtyModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(
            PresenterModule::class,
            AdapterModule::class,
            PagingModule::class
    ))
    abstract fun bindMainActivity(): MainActivity


}