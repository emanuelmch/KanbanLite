package bill.kanban.home

import bill.kanban.common.Presenter
import javax.inject.Inject

class HomePresenter @Inject constructor(private val view: HomeView) : Presenter {

    override fun onViewReady() {
        val todo = listOf(KanbanCard("To Do 1"), KanbanCard("To Do 2"))
        val doing = listOf(KanbanCard("Doing 1"))
        val done = listOf(KanbanCard("Done 1"), KanbanCard("Done 2"), KanbanCard("Done 3"))
        view.render(HomeViewState.Ready(todo, doing, done))
    }
}

interface HomeView {
    fun render(viewState: HomeViewState)
}

sealed class HomeViewState {
    data class Ready(val todo: List<KanbanCard>, val doing: List<KanbanCard>, val done: List<KanbanCard>) : HomeViewState()
}

data class KanbanCard(val title: String)
