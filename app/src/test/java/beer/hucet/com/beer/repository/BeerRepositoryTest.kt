package beer.hucet.com.beer.repository

import beer.hucet.com.beer.datasource.NetworkDataSource
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.amshove.kluent.mock
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * Created by taesu on 2017-12-05.
 */
class BeerRepositoryTest : SubjectSpek<BeerRepository>({

    val networkDatasource by memoized {
        mock<NetworkDataSource>()
    }
    given("BeerRepository")
    {
        subject {
            BeerRepository(networkDatasource, mock(), mock())
        }

        beforeEachTest {
            whenever(networkDatasource.getPageBeers(1, 1)).thenReturn(Single.just(listOf()))
        }
        on("LoadState [Complete]")
        {

            val testSubscribe = subject.getPagingBeers(1, 1).test()
            it("Assert complete, noErrors")
            {
                testSubscribe.assertComplete()
                testSubscribe.assertNoErrors()
            }
        }
        on("LoadState [Error]")
        {

            whenever(networkDatasource.getPageBeers(1, 1))
                    .thenReturn(Single.just(1).map { throw RuntimeException() })
            val testSubscribe = subject.getPagingBeers(1, 1).test()
            it("Assert noComplete, error") {
                testSubscribe.assertError(RuntimeException::class.java)
                testSubscribe.assertNotComplete()
            }
        }
    }
})