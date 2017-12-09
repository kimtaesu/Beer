package beer.hucet.com.beer.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import beer.hucet.com.beer.scheduler.DefaultSchedulerProvider
import beer.hucet.com.beer.scheduler.SchedulerProvider
import beer.hucet.com.beer.viewmodel.BeerViewModel
import beer.hucet.com.beer.viewmodel.BeerViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Created by taesu on 2017-12-07.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BeerViewModel::class)
    abstract fun bindBeerViewModel(beerViewModel: BeerViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: BeerViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindSchedulerProvider(de: DefaultSchedulerProvider): SchedulerProvider


    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}