package beer.hucet.com.beer.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.fixture.BeerFixture
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.preference.PreferenceWrapper
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.TestSchedulerProvider
import beer.hucet.com.beer.usecase.BeerUseCase
import beer.hucet.com.beer.view.paging.LoadState
import beer.hucet.com.beer.view.paging.ResourcePage
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.any
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit


/**
 * Created by taesu on 2017-12-09.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class BeerViewModelTest {
    @Mock private lateinit var observer: Observer<List<Beer>>
    @Mock private lateinit var stateObser: Observer<ResourcePage>
    @Mock private lateinit var errorObser: Observer<String>
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var beerViewModel: BeerViewModel
    private lateinit var useCase: BeerUseCase
    @Mock private lateinit var repository: BeerRepository
    @Mock private lateinit var pref: PreferenceWrapper
    private val testScheduler = TestScheduler()
    private val testData = BeerFixture.deserializeBeers("default_punk.json")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(pref.getPerPageSize()).thenReturn(10)
        whenever(repository.getPagingBeers(any(), any())).thenReturn(Single.just(testData))
        useCase = BeerUseCase(repository)
        beerViewModel = BeerViewModel(useCase, pref, TestSchedulerProvider(testScheduler))
    }

    @After
    fun after() {
    }

    @Test
    fun loadFetch() {
        beerViewModel.getBeersLivData().observeForever(observer)
        beerViewModel.getLoadMoreLiveData().observeForever(stateObser)
        beerViewModel.requestFetch()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(observer, times(1)).onChanged(testData)
        val inOrder = inOrder(stateObser, stateObser)
        inOrder.verify(stateObser).onChanged(ResourcePage(LoadState.LOADING, false))
        inOrder.verify(stateObser).onChanged(ResourcePage(LoadState.SUCCESS, false))
    }

    @Test
    fun errorFetch() {
        val errorMsg = "ABC"
        whenever(repository.getPagingBeers(any(), any())).thenReturn(Single.just(1).map { throw RuntimeException(errorMsg) })

        beerViewModel.getErrorLiveData().observeForever(errorObser)
        beerViewModel.getBeersLivData().observeForever(observer)
        beerViewModel.getLoadMoreLiveData().observeForever(stateObser)
        beerViewModel.requestFetch()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(observer, never()).onChanged(testData)
        val inOrder = inOrder(stateObser, stateObser)
        inOrder.verify(stateObser).onChanged(ResourcePage(LoadState.LOADING, false))
        inOrder.verify(stateObser).onChanged(ResourcePage(LoadState.ERROR, false))

        verify(errorObser).onChanged(errorMsg)
    }
}