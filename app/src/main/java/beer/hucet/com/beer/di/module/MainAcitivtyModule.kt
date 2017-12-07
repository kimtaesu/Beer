package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by taesu on 2017-10-30.
 */
@Module
abstract class MainAcitivtyModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(
            AdapterModule::class
    ))
    abstract fun bindMainActivity(): MainActivity


}