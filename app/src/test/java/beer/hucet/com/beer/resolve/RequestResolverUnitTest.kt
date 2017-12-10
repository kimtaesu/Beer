package beer.hucet.com.beer.resolve

import beer.hucet.com.beer.datasource.NetworkDataSource
import beer.hucet.com.beer.fixture.BeerFixture
import beer.hucet.com.beer.persistence.BeerDao
import beer.hucet.com.beer.persistence.BeerDatabase
import beer.hucet.com.beer.view.paging.Paging
import beer.hucet.com.beer.view.paging.RateLimiter
import beer.hucet.com.beer.view.paging.UpTimeProvider
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by taesu on 2017-12-10.
 */

class RequestResolverUnitTest {
    val testData = BeerFixture.deserializeBeers("default_punk.json")

    private lateinit var resolver: RequestResolver
    @Mock private lateinit var networkDataSource: NetworkDataSource
    @Mock private lateinit var db: BeerDatabase
    @Mock private lateinit var rateLimit: RateLimiter
    @Mock private lateinit var upTimeProvider: UpTimeProvider
    @Mock private lateinit var dao: BeerDao
    @Before
    fun createService() {
        MockitoAnnotations.initMocks(this)
        whenever(networkDataSource.getPageBeers(any(), any())).thenReturn(Single.just(testData))
        whenever(upTimeProvider.provide(any())).thenReturn(1)
        whenever(rateLimit.shouldFetch(any())).thenReturn(true)
        whenever(db.beerDao()).thenReturn(dao)
        whenever(dao.getAllBeers()).thenReturn(Single.just(testData))
        resolver = RequestResolver(networkDataSource, db, rateLimit, upTimeProvider)
    }

    @Test
    fun networkDbInserAllBeers() {
        resolver.resolve(Paging(ResolveType.Normal(), 1, 2))
                .test()
                .assertComplete()
                .assertValue { it == testData }

        verify(dao, times(1)).insertAll(testData)
    }

    @Test
    fun dbCahcedAllBeers() {
        whenever(rateLimit.shouldFetch(any())).thenReturn(false)

        resolver.resolve(Paging(ResolveType.Normal(), 1, 2))
                .test()
                .assertComplete()
                .assertValue { it == testData }

        verify(networkDataSource, never()).getPageBeers(any(), any())
        verify(dao, times(1)).getAllBeers()
    }
}