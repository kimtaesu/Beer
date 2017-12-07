package beer.hucet.com.beer.di.module

import android.support.v7.widget.RecyclerView
import beer.hucet.com.beer.di.scopes.PerActivity
import beer.hucet.com.beer.model.Basic
import beer.hucet.com.beer.view.adapter.BeerAdapter
import beer.hucet.com.beer.view.adapter.ItemType
import beer.hucet.com.beer.view.adapter.ViewDelegateAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by taesu on 2017-12-04.
 */
@Module
class AdapterModule {

    @PerActivity
    @Provides
    fun provideAdapter(): BeerAdapter {
        val map = mapOf(ItemType.Beer.type to ViewDelegateAdapter.BeerDelegate())
        return BeerAdapter(map as Map<Int, ViewDelegateAdapter<RecyclerView.ViewHolder, Basic>>)
    }
}