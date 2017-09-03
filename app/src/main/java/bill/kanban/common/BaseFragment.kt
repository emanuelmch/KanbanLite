package bill.kanban.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class BaseFragment<out P : Presenter> : Fragment() {

    abstract val presenter: P

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()
    }
}

interface Presenter {
    fun onViewReady()
}
