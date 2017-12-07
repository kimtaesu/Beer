package beer.hucet.com.beer.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import beer.hucet.com.beer.R
import beer.hucet.com.beer.glide.GlideApp
import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.presenter.BeerRequest
import beer.hucet.com.beer.view.adapter.BeerAdapter
import beer.hucet.com.beer.view.adapter.BeerViewHolder
import beer.hucet.com.beer.view.paging.LinearEndScrollListener
import beer.hucet.com.beer.view.paging.PagingAvailability
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, BeerRequest.View {


    @Inject lateinit var presenter: BeerRequest.Presenter
    @Inject lateinit var adapter: BeerAdapter
    @Inject lateinit var pagingAvailability: PagingAvailability

    private var curPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        requestFetch(curPage, 30)
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
            if (pagingAvailability.availablePaging()) {
                requestFetch(++curPage, 30)
            }
        }))
    }

    private fun startDetailActivity(beer: Beer) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_BEER, beer)
        startActivity(intent)
    }

    private fun requestFetch(page: Int, perPage: Int) {
        Timber.d("requstFetch ${page} / ${perPage} ")
        presenter.requestFetch(page, perPage)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelFetch()
    }

    override fun update(items: List<Beer>) {
        adapter.update(items)
    }

    override fun showProgress() {
//        Toast.makeText(this, "showProgress", Toast.LENGTH_SHORT).show()
    }

    override fun hideProgress() {
//        Toast.makeText(this, "hideProgress", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
//        Toast.makeText(this, "showError", Toast.LENGTH_SHORT).show()
    }


    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
