package beer.hucet.com.beer.view

import beer.hucet.com.beer.BuildConfig
import beer.hucet.com.beer.TestApplication
import beer.hucet.com.beer.presenter.BeerPresenter
import org.amshove.kluent.`should not be`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by taesu on 2017-12-05.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21), application = TestApplication::class)
class MainActivityTest {
    @InjectMocks lateinit var presenter: BeerPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun ABC() {
        presenter `should not be` null
    }
}