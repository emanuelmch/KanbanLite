package bill.kanban.home.stages

import bill.kanban.atomic.AtomicPresenter
import bill.kanban.atomic.AtomicView
import bill.kanban.infra.observeOnMainThread
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class StagePresenter @Inject constructor(private val interactor: StageInteractor,
                                         @Named(StageDependencies.STAGE_ID) private val stageId: Int) : AtomicPresenter<StageAtom, StageAction>() {

    private var disposable: Disposable? = null

    override val initialState = StageAtom.Loading()

    override fun onViewAttached(view: AtomicView<StageAtom>) {
        disposable = interactor.getKanbanStageById(stageId)
                .observeOnMainThread()
                .subscribeBy(onError = { err -> view.render(StageAtom.Error(err)) },
                        onSuccess = { view.render(StageAtom.Ready(it)) })
    }

    override fun beforeViewDetached() {
        disposable?.dispose()
        disposable = null
    }

    override fun reduce(atom: StageAtom, action: StageAction): StageAtom {
        TODO("not implemented")
    }
}

sealed class StageAtom {
    class Loading : StageAtom()
    class Error(val error: Throwable) : StageAtom()
    class Ready(val stage: KanbanStage) : StageAtom()
}

sealed class StageAction

class KanbanStage(val title: String, val cards: List<KanbanCard>)
class KanbanCard(val title: String)
