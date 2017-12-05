package vingle.hucet.com.vingle.di.module

import beer.hucet.com.beer.view.MainActivity
import com.hucet.clean.gallery.inject.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by taesu on 2017-10-30.
 */
@Module
abstract class MainAcitivtyModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(
            PresenterModule::class,
            AdapterModule::class
    ))
    abstract fun bindMainActivity(): MainActivity


}