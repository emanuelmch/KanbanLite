package bill.kanban.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bill.kanban.R
import bill.kanban.atomic.AtomicFragment
import bill.kanban.home.stages.StageFragment
import kotlinx.android.synthetic.main.home_core.*
import javax.inject.Inject

class HomeFragment : AtomicFragment<HomeAtom, HomeAction>() {

    @Inject override lateinit var presenter: HomePresenter

    private val stagesAdapter: StagesAdapter by lazy { StagesAdapter() }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        HomeDependencies.setup(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.home_core, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stagesContainer.adapter = stagesAdapter
    }

    override fun render(atom: HomeAtom) =
            when (atom) {
                is HomeAtom.Loading -> renderLoading()
                is HomeAtom.Empty -> renderEmpty()
                is HomeAtom.Ready -> renderReady(atom.stageIds)
            }

    private fun renderLoading() {
        // TODO("not implemented")
    }

    private fun renderEmpty() {
        // TODO("not implemented")
    }

    private fun renderReady(stages: List<Int>) {
        stagesAdapter.stageIds = stages
    }

    private inner class StagesAdapter : FragmentPagerAdapter(childFragmentManager) {
        var stageIds = emptyList<Int>()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getCount() = stageIds.size
        override fun getItem(position: Int) = StageFragment.newInstance(stageIds[position])
    }
}

