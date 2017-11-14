package bill.kanban.atomic

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class AtomicFragment<Atom> : Fragment(), AtomicView<Atom> {

    abstract val presenter: AtomicPresenter<Atom>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }
}

