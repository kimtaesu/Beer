package beer.hucet.com.beer.fixture

import beer.hucet.com.beer.api.PunkApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
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

    fun providePunkApi(mockWebServer: MockWebServer): PunkApi {
        return Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PunkApi::class.java)
    }
}