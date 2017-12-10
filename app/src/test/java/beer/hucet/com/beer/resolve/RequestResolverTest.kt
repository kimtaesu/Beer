package beer.hucet.com.beer.resolve

import beer.hucet.com.beer.api.PunkApi
import beer.hucet.com.beer.fixture.BeerFixture
import beer.hucet.com.beer.fixture.MockServerFixture
import beer.hucet.com.beer.fixture.MockServerFixture.providePunkApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

/**
 * Created by taesu on 2017-12-10.
 */

class RequestResolverTest {
    private lateinit var service: PunkApi
    private lateinit var mockWebServer: MockWebServer

    private lateinit var resolver: RequestResolver
    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = providePunkApi(mockWebServer)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Ignore
    @Test
    fun networkDbInserAllBeers() {
        val testData = BeerFixture.deserializeBeers("default_punk.json")
        MockServerFixture.enqueueResponse(mockWebServer, "default_punk.json")
        service
                .getPageBeer(1, 2)
                .test()
                .assertComplete()
                .assertValue {
                    it.body() == testData
                }
    }
}