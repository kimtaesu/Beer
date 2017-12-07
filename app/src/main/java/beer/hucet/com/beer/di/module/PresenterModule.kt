package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.presenter.BeerPresenter
import beer.hucet.com.beer.presenter.BeerRequest
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.view.MainActivity
import beer.hucet.com.beer.view.paging.LoadingChecker
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

/**
 * Created by taesu on 2017-11-10.
 */
@Module
class PresenterModule {
    @Provides
    @PerActivity
    fun provideBeerPresenter(view: Set<@JvmSuppressWildcards BeerRequest.View>, repository: BeerRepository): BeerRequest.Presenter =
            BeerPresenter(view, repository)

    @Provides
    @PerActivity
    @ElementsIntoSet
    fun provideBeerView(mainActivity: MainActivity, loadingChecker: LoadingChecker): Set<BeerRequest.View> =
            setOf(mainActivity, loadingChecker)

}