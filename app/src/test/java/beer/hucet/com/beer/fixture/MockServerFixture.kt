package beer.hucet.com.beer.fixture

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * Created by taesu on 2017-12-07.
 */
object MockServerFixture {

    @Throws(IOException::class)
    fun enqueueResponse(mockWebServer: MockWebServer, fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream(fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }
}