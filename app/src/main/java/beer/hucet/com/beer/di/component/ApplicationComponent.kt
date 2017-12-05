package vingle.hucet.com.vingle.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.buffer.android.boilerplate.ui.injection.module.ApplicationModule
import vingle.hucet.com.vingle.di.module.MainAcitivtyModule
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

    fun inject(app: Application)
}
