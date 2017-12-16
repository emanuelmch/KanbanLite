package bill.kanban.infra

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.disposeWith(disposable: CompositeDisposable) {
    disposable.add(this)
}

fun <T> Single<T>.observeOnMainThread() = this.observeOn(AndroidSchedulers.mainThread())!!

fun <T> Observable<T>.subscribeOnComputationThread() = this.subscribeOn(Schedulers.computation())!!
fun <T> Observable<T>.observeOnMainThread() = this.observeOn(AndroidSchedulers.mainThread())!!
