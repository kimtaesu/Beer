package beer.hucet.com.beer.view.paging

import beer.hucet.com.beer.model.Beer
import beer.hucet.com.beer.presenter.BeerRequest
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * Created by taesu on 2017-12-05.
 */
class LoadingChecker : Observable<LoadingState>(), BeerRequest.View {

    private var listener: Listener? = null
    override fun subscribeActual(observer: Observer<in LoadingState>) {
        listener = Listener(observer)
        listener?.run {
            observer.onSubscribe(this)
        }
    }

    override fun showProgress() {
        listener?.showProgress()
    }

    override fun hideProgress() {
        listener?.hideProgress()
    }

    override fun showError() {
        listener?.showError()
    }

    override fun update(items: List<Beer>) {
    }


    private inner class Listener(private val observer: Observer<in LoadingState>) : BeerRequest.View, MainThreadDisposable() {
        override fun update(items: List<Beer>) {
        }

        override fun onDispose() {
            observer.onComplete()
        }

        override fun showProgress() {
            if (!isDisposed) {
                observer.onNext(LoadingState.Loading)
            }

        }

        override fun hideProgress() {
            if (!isDisposed) {
                observer.onNext(LoadingState.Complete)
            }
        }

        override fun showError() {
            if (!isDisposed) {
                observer.onNext(LoadingState.Complete)
            }
        }
    }
}