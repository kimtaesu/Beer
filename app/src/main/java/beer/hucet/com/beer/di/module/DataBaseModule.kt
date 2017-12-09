package beer.hucet.com.beer.di.module

import android.app.Application
import beer.hucet.com.beer.persistence.BeerDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-09.
 */
@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(application: Application): BeerDatabase =
            BeerDatabase.getInstance(application)
}