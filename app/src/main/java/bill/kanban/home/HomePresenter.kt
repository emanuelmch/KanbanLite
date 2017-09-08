package bill.kanban.home

import bill.kanban.atomic.AtomicPresenter
import bill.kanban.atomic.AtomicView
import javax.inject.Inject

class HomePresenter @Inject constructor(private val view: AtomicView<HomeAtom>) : AtomicPresenter<HomeAtom> {

    override fun onViewReady() {
        val todo = listOf(KanbanCard("To Do 1"), KanbanCard("To Do 2"))
        val doing = listOf(KanbanCard("Doing 1"))
        val done = listOf(KanbanCard("Done 1"), KanbanCard("Done 2"), KanbanCard("Done 3"))
        view.render(HomeAtom.Ready(todo, doing, done))
    }
}

sealed class HomeAtom {
    data class Ready(val todo: List<KanbanCard>, val doing: List<KanbanCard>, val done: List<KanbanCard>) : HomeAtom()
}

data class KanbanCard(val title: String)
