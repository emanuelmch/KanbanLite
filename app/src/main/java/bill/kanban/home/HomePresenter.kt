package bill.kanban.home

import bill.kanban.common.Presenter
import javax.inject.Inject

class HomePresenter @Inject constructor(private val view: HomeView) : Presenter {

    override fun onViewReady() {
        view.render(HomeViewState.Ready("Hello from Presenter!"))
    }
}

sealed class HomeViewState {
    data class Ready(val message: String) : HomeViewState()
}

interface HomeView {
    fun render(viewState: HomeViewState)
}