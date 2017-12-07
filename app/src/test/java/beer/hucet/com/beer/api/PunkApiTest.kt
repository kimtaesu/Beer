package beer.hucet.com.beer.api

import beer.hucet.com.beer.fixture.BeerFixture
import beer.hucet.com.beer.fixture.MockServerFixture
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.`should equal to`
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

/**
 * Created by taesu on 2017-12-07.
 */
class PunkApiTest {
    private lateinit var service: PunkApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    @Throws(IOException::class)
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PunkApi::class.java)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getBeers() {
        val testData = BeerFixture.deserializeBeers("default_punk.json")
        MockServerFixture.enqueueResponse(mockWebServer, "default_punk.json")
        service
                .getPagingBeer(1, 1)
                .test()
                .assertValue {
                    it == testData
                }
        mockWebServer.takeRequest().path `should equal to` "/beers?page=1&per_page=1"
    }
}