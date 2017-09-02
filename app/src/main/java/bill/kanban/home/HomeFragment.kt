package bill.kanban.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bill.kanban.R
import kotlinx.android.synthetic.main.home_core.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeView {

    @Inject lateinit var presenter: HomePresenter

    override fun onAttach(context: Context?) {
        HomeDependencies.setup(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.home_core, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()
    }

    override fun render(viewState: HomeViewState) =
            when (viewState) {
                is HomeViewState.Ready -> this.message.text = viewState.message
            }
}
