package bill.kanban.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import bill.kanban.R
import bill.kanban.atomic.AtomicFragment
import bill.kanban.ext.inflate
import kotlinx.android.synthetic.main.home_card.view.*
import kotlinx.android.synthetic.main.home_core.*
import javax.inject.Inject

class HomeFragment : AtomicFragment<HomeAtom>() {

    @Inject override lateinit var presenter: HomePresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        HomeDependencies.setup(this)
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater!!.inflate(R.layout.home_core, container, false)!!

    override fun render(atom: HomeAtom) =
            when (atom) {
                is HomeAtom.Ready -> renderReady(atom)
            }

    private fun renderReady(atom: HomeAtom.Ready) {
        todoContainer.removeAllViews()
        doingContainer.removeAllViews()
        doneContainer.removeAllViews()

        atom.todo.map { toLayout(it, todoContainer) }.forEach { todoContainer.addView(it) }
        atom.doing.map { toLayout(it, doingContainer) }.forEach { doingContainer.addView(it) }
        atom.done.map { toLayout(it, doneContainer) }.forEach { doneContainer.addView(it) }
    }

    private fun toLayout(card: KanbanCard, container: ViewGroup) =
            inflate(R.layout.home_card, container, false).apply {
                title.text = card.title
            }

}
