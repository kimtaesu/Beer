package beer.hucet.com.beer.repository

import beer.hucet.com.beer.resolve.RequestResolver
import beer.hucet.com.beer.resolve.ResolveType
import beer.hucet.com.beer.view.paging.Paging
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

    val resolver by memoized {
        mock<RequestResolver>()
    }

    given("BeerRepository")
    {
        subject {
            BeerRepository(resolver)
        }

        beforeEachTest {
            whenever(resolver.resolve(any())).thenReturn(Single.just(listOf()))
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

            whenever(resolver.resolve(any()))
                    .thenReturn(Single.just(1).map { throw RuntimeException() })
            val testSubscribe = subject.getPagingBeers(Paging(ResolveType.Normal(), 1, 1)).test()
            it("Assert noComplete, error") {
                testSubscribe.assertError(RuntimeException::class.java)
                testSubscribe.assertNotComplete()
            }
        }
    }
})