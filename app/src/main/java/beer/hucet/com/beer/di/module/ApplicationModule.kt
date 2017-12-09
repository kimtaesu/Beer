package beer.hucet.com.beer.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module(includes = arrayOf(
        RepositoryModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DataBaseModule::class
))
open class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}
