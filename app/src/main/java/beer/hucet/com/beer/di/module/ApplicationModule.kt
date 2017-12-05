package org.buffer.android.boilerplate.ui.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import vingle.hucet.com.vingle.di.module.NetworkModule
import vingle.hucet.com.vingle.di.module.RepositoryModule
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module(includes = arrayOf(
        RepositoryModule::class,
        NetworkModule::class
))
open class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}
