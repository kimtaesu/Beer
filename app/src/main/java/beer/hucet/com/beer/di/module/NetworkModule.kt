package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.BuildConfig
import beer.hucet.com.beer.api.PunkApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Singleton

/**
 * Created by taesu on 2017-11-13.
 */
@Module(includes = arrayOf())
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)

            builder.addInterceptor({ chain ->
                var req = chain.request()
                chain.proceed(req)
            })
        }

        builder.connectTimeout((60 * 1000).toLong(), MILLISECONDS)
                .readTimeout((60 * 1000).toLong(), MILLISECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRestAdapter(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
        return builder.build()
    }


    @Provides
    @Singleton
    fun providePunkApi(retrofit: Retrofit): PunkApi {
        return retrofit.create(PunkApi::class.java)
    }
}