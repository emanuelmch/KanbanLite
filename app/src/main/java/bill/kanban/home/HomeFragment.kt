package bill.kanban.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bill.kanban.R
import bill.kanban.common.BaseFragment
import kotlinx.android.synthetic.main.home_card.view.*
import kotlinx.android.synthetic.main.home_core.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomePresenter>(), HomeView {

    @Inject override lateinit var presenter: HomePresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        HomeDependencies.setup(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.home_core, container, false)
    }

    override fun render(viewState: HomeViewState) =
            when (viewState) {
                is HomeViewState.Ready -> renderReady(viewState)
            }

    private fun renderReady(viewState: HomeViewState.Ready) {
        todoContainer.removeAllViews()
        doingContainer.removeAllViews()
        doneContainer.removeAllViews()

        viewState.todo.map { toLayout(it, todoContainer) }.forEach { todoContainer.addView(it) }
        viewState.doing.map { toLayout(it, doingContainer) }.forEach { doingContainer.addView(it) }
        viewState.done.map { toLayout(it, doneContainer) }.forEach { doneContainer.addView(it) }
    }

    private fun toLayout(card: KanbanCard, container: ViewGroup) =
            LayoutInflater.from(context).inflate(R.layout.home_card, container, false).apply {
                title.text = card.title
            }
}
