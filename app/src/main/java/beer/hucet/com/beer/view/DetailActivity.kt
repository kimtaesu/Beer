package beer.hucet.com.beer.view

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import beer.hucet.com.beer.R
import beer.hucet.com.beer.model.Beer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by taesu on 2017-12-06.
 */
class DetailActivity : AppCompatActivity() {

    private val beer by lazy { intent.getSerializableExtra(KEY_BEER) as Beer }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    private fun initViews() {

        name.text = beer.name
        abv.text = getString(R.string.title_abv, beer.abv)
        ibu.text = getString(R.string.title_ibu, beer.ibu)
        ebc.text = getString(R.string.title_ebc, beer.ebc)
        srm.text = getString(R.string.title_srm, beer.srm)
        ph.text = getString(R.string.title_ph, beer.ph)
        desc.text = beer.description
        Glide.with(this)
                .load(beer.image_url)
                .into(thumbnail)
    }

    companion object {
        val KEY_BEER = "KEY_BEER"
    }
}