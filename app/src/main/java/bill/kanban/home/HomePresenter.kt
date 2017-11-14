package bill.kanban.home

import bill.kanban.atomic.AtomicPresenter
import bill.kanban.atomic.AtomicView
import javax.inject.Inject

class HomePresenter @Inject constructor() : AtomicPresenter<HomeAtom> {

    override fun attach(view: AtomicView<HomeAtom>) {
        // FIXME: This should be in HomeInteractor
        view.render(HomeAtom.Ready(listOf(1, 2, 3)))
    }
}

sealed class HomeAtom {
    class Ready(val stageIds: List<Int>) : HomeAtom()
}


