package bill.kanban.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bill.kanban.R
import bill.kanban.common.BaseFragment
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
                is HomeViewState.Ready -> this.message.text = viewState.message
            }
}
