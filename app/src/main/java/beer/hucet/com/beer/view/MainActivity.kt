package beer.hucet.com.beer.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import beer.hucet.com.beer.R
import beer.hucet.com.beer.presenter.BeerRequest
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, BeerRequest.View {

    @Inject lateinit var presenter: BeerRequest.Presenter
    @Inject lateinit var adapter: BeerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        requestFetch()
    }

    private fun initRecycler() {
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
        }
    }

    private fun requestFetch() {
        presenter.getBeer(1, 10)
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
