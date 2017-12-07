package beer.hucet.com.beer.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import beer.hucet.com.beer.R
import beer.hucet.com.beer.databinding.ActivityMainBinding
import beer.hucet.com.beer.glide.GlideApp
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.view.adapter.BeerAdapter
import beer.hucet.com.beer.view.adapter.BeerViewHolder
import beer.hucet.com.beer.view.paging.LinearEndScrollListener
import beer.hucet.com.beer.viewmodel.BeerViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {


    @Inject lateinit var adapter: BeerAdapter
    @Inject lateinit var beerViewModel: BeerViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObservr()
        initRecycler()
        initLoadMore()
    }

    private fun initObservr() {
        beerViewModel.getBeersLivData().observe(this, Observer {
            adapter.update(it!!)
        })
    }

    private fun initLoadMore() {
        beerViewModel.getLoadMoreLiveData().observe(this, Observer {
            binding.loadState = it?.getLoadState()
        })
    }

    private fun initRecycler() {
        adapter.setGlideRequest(GlideApp.with(this))

        val linearLayoutManager = LinearLayoutManager(this)
        adapter.setOnClickListener(linearLayoutManager, { beer ->
            startDetailActivity(beer)
        })
        recycler.apply {
            layoutManager = linearLayoutManager
            adapter = this@MainActivity.adapter
        }
        recycler.setRecyclerListener {
            if (it is BeerViewHolder) {
                it.thumbnail.clearAnimation()
                GlideApp.with(this).clear(it.thumbnail)
            }

        }
        recycler.addOnScrollListener(LinearEndScrollListener(linearLayoutManager, {
            beerViewModel.requestFetch()
        }))

        beerViewModel.requestFetch()
    }


    private fun startDetailActivity(beer: Beer) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_BEER, beer)
        startActivity(intent)
    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
