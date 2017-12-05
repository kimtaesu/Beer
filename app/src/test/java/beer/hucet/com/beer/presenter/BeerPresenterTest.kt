package beer.hucet.com.beer.presenter

import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.scheduler.TestSchedulerProvider
import beer.hucet.com.beer.view.adapter.BeerAdapter
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.any
import org.amshove.kluent.mock
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import java.util.concurrent.TimeUnit

/**
 * Created by taesu on 2017-12-05.
 */
class BeerPresenterTest : SubjectSpek<BeerPresenter>({

    val view by memoized { mock<BeerRequest.View>() }
    val repository by memoized { mock<BeerRepository>() }
    val adapter by memoized { mock<BeerAdapter>() }
    val testScheduler by memoized { TestScheduler() }
    given("BeerPresenter")
    {
        beforeEachTest {
            whenever(repository.getPagingBeer(any(), any())).thenReturn(Flowable.just(listOf()))

        }
        subject {
            BeerPresenter(setOf(view), repository, adapter, TestSchedulerProvider(testScheduler))
        }
        on("getBeer 성공 ")
        {
            subject.getBeer(1, 1)
            testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

            it("Success 경우 호출")
            {
                verify(view, times(1)).showProgress()
                verify(view, times(1)).hideProgress()

                verify(adapter, times(1)).update(any())
                verify(repository, times(1)).getPagingBeer(any(), any())

            }
        }
        on("getBeer 실패")
        {
            whenever(repository.getPagingBeer(any(), any())).thenReturn(Flowable.just(1).map { throw RuntimeException() })

            subject.getBeer(1, 1)
            testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

            it("실패 할 경우 호출")
            {
                verify(view, times(1)).showProgress()
                verify(view, times(1)).hideProgress()
                verify(view, times(1)).showError()

                verify(adapter, never()).update(any())
                verify(repository, times(1)).getPagingBeer(any(), any())

            }
        }

    }
})