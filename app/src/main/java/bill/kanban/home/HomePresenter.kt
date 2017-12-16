package bill.kanban.home

import bill.kanban.atomic.AtomicPresenter
import bill.kanban.atomic.AtomicView
import bill.kanban.infra.disposeWith
import bill.kanban.infra.observeOnMainThread
import bill.kanban.infra.subscribeOnComputationThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomePresenter @Inject constructor(private val interactor: HomeInteractor) : AtomicPresenter<HomeAtom, HomeAction>() {

    private val disposables = CompositeDisposable()

    override val initialState = HomeAtom.Loading()

    override fun onViewAttached(view: AtomicView<HomeAtom>) {
        interactor.loadStages()
                .observeOnMainThread()
                .subscribe { stages -> emitAction(HomeAction.ReplaceStages(stages)) }
                .disposeWith(disposables)
    }

    override fun beforeViewDetached() {
        disposables.clear()
    }

    override fun reduce(atom: HomeAtom, action: HomeAction) =
            when (action) {
                is HomeAction.ReplaceStages ->
                    if (action.stages.isEmpty()) {
                        HomeAtom.Empty()
                    } else {
                        HomeAtom.Ready(action.stages)
                    }
            }
}

class HomeInteractor @Inject constructor() {
    fun loadStages() = Observable.timer(3, TimeUnit.SECONDS).map { listOf(1, 2, 3) }.subscribeOnComputationThread()
}

sealed class HomeAction {
    class ReplaceStages(val stages: List<Int>) : HomeAction()
}

sealed class HomeAtom {
    class Loading : HomeAtom()
    class Empty : HomeAtom()
    class Ready(val stageIds: List<Int>) : HomeAtom()
}


