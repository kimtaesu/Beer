package beer.hucet.com.beer.di.component

import android.app.Application
import beer.hucet.com.beer.BeerApplication
import beer.hucet.com.beer.di.module.ApplicationModule
import beer.hucet.com.beer.di.module.MainAcitivtyModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by taesu on 2017-10-30.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        MainAcitivtyModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: BeerApplication)
}
