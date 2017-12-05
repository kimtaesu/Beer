package beer.hucet.com.beer.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import beer.hucet.com.beer.R
import beer.hucet.com.beer.glide.GlideApp
import beer.hucet.com.beer.presenter.BeerRequest
import beer.hucet.com.beer.view.adapter.BeerAdapter
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
        requestFetch(curPage, 10)
    }


    private fun initRecycler() {
        adapter.setGlideRequest(GlideApp.with(this))
        val linearLayoutManager = LinearLayoutManager(this)
        recycler.apply {
            layoutManager = linearLayoutManager
            adapter = this@MainActivity.adapter
        }

        recycler.addOnScrollListener(LinearEndScrollListener(linearLayoutManager, {
            if (pagingAvailability.availablePaging()) {
                requestFetch(++curPage, 10)
            }
        }))
    }

    private fun requestFetch(page: Int, perPage: Int) {
        Timber.d("requstFetch ${page} / ${perPage} ")
        presenter.getBeer(page, perPage)
    }

    override fun showProgress() {
        Toast.makeText(this, "showProgress", Toast.LENGTH_SHORT).show()
    }

    override fun hideProgress() {
        Toast.makeText(this, "hideProgress", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(this, "showError", Toast.LENGTH_SHORT).show()
    }


    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
