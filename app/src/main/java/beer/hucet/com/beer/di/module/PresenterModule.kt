package beer.hucet.com.beer.di.module

import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.presenter.BeerPresenter
import beer.hucet.com.beer.presenter.BeerRequest
import beer.hucet.com.beer.repository.BeerRepository
import beer.hucet.com.beer.view.BeerAdapter
import beer.hucet.com.beer.view.MainActivity
import dagger.Module
import dagger.Provides

/**
 * Created by taesu on 2017-11-10.
 */
@Module
class PresenterModule {
    @Provides
    @PerActivity
    fun provideBeerPresenter(view: BeerRequest.View, repository: BeerRepository, adapter: BeerAdapter): BeerRequest.Presenter =
            BeerPresenter(view, repository, adapter)

    @Provides
    @PerActivity
    fun provideBeerView(mainActivity: MainActivity): BeerRequest.View = mainActivity

}