package beer.hucet.com.beer.datasource

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.exception.NetworkException
import beer.hucet.com.beer.fixture.MockServerFixture
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by taesu on 2017-12-09.
 */
class NetworkDataSourceTest {
    private lateinit var service: PunkApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var networkDataSource: NetworkDataSource
    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PunkApi::class.java)
        networkDataSource = NetworkDataSource(service)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun networkError() {
        MockServerFixture.enqueueResponse(mockWebServer, "default_punk.json", {
            setResponseCode(400)
        })
        networkDataSource
                .getPageBeers(1, 1)
                .doOnError { }
                .test()
                .assertError(NetworkException::class.java)
    }
}