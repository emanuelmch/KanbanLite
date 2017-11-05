package bill.kanban.home.stages

import bill.kanban.atomic.AtomicPresenter
import bill.kanban.atomic.AtomicView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class StagePresenter
@Inject constructor(val view: AtomicView<StageAtom>,
                    val interactor: StageInteractor,
                    @Named(StageDependencies.STAGE_ID) val stageId: Int)
    : AtomicPresenter<StageAtom> {

    private var disposable: Disposable? = null

    override fun onViewReady() {
        view.render(StageAtom.Loading())
        disposable = interactor.getKanbanStageById(stageId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = { err -> view.render(StageAtom.Error(err)) },
                        onSuccess = { view.render(StageAtom.Ready(it)) })
    }

    override fun onViewDestroy() {
        disposable?.dispose()
    }
}


sealed class StageAtom {
    class Loading : StageAtom()
    class Error(val error: Throwable) : StageAtom()
    class Ready(val stage: KanbanStage) : StageAtom()
}

class KanbanStage(val title: String, val cards: List<KanbanCard>)
class KanbanCard(val title: String)