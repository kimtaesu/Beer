package beer.hucet.com.beer.repository

import beer.hucet.com.beer.ResolveType
import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.view.paging.Paging
import beer.hucet.com.beer.view.paging.UpTimeProvider
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.amshove.kluent.any
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

    val upTimeProvider by memoized {
        mock<UpTimeProvider>()
    }

    given("BeerRepository")
    {
        subject {
            BeerRepository(networkDatasource, mock(), mock(), upTimeProvider)
        }

        beforeEachTest {
            whenever(networkDatasource.getPageBeers(any(), any())).thenReturn(Single.just(listOf()))
            whenever(upTimeProvider.resolve(any())).thenReturn(10)
        }
        on("LoadState [Complete]")
        {

            val testSubscribe = subject.getPagingBeers(Paging(ResolveType.Normal(), 1, 1)).test()
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
            val testSubscribe = subject.getPagingBeers(Paging(ResolveType.Normal(), 1, 1)).test()
            it("Assert noComplete, error") {
                testSubscribe.assertError(RuntimeException::class.java)
                testSubscribe.assertNotComplete()
            }
        }
    }
})