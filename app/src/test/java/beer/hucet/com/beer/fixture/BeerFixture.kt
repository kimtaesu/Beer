package beer.hucet.com.beer.fixture

import beer.hucet.com.beer.model.Beer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by taesu on 2017-12-05.
 */
object BeerFixture {

    fun deserializeBeers(path: String): List<Beer> {
        return parseRes(path)
    }

    private fun parseRes(path: String): List<Beer> {
        val jsonString = this.javaClass.classLoader.getResource(path).readText()
        val fakeMediaType = object : TypeToken<List<Beer>>() {}.type
        return Gson().fromJson(jsonString, fakeMediaType)
    }
}