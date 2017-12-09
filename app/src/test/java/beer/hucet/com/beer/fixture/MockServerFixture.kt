package beer.hucet.com.beer.fixture

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import java.nio.charset.StandardCharsets

/**
 * Created by taesu on 2017-12-07.
 */
object MockServerFixture {
    fun enqueueResponse(mockWebServer: MockWebServer, fileName: String, mockInit: MockResponse.() -> Unit = { MockResponse() }) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream(fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse().apply { mockInit() }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }
}