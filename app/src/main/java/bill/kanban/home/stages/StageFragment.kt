package bill.kanban.home.stages

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bill.kanban.R
import bill.kanban.atomic.AtomicFragment
import bill.kanban.infra.inflate
import bill.kanban.infra.withArgument
import kotlinx.android.synthetic.main.home_stage.*
import kotlinx.android.synthetic.main.home_stage.view.*
import timber.log.Timber
import javax.inject.Inject

class StageFragment : AtomicFragment<StageAtom>() {

    companion object {
        private val ARG_STAGE_ID = "STAGE_ID"
        fun newInstance(stageId: Int) = StageFragment()
                .withArgument(ARG_STAGE_ID, stageId)
    }

    @Inject override lateinit var presenter: StagePresenter
    private val cardsAdapter by lazy { CardsAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val stageId = arguments!!.getInt(ARG_STAGE_ID)
        StageDependencies.setup(this, stageId)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.home_stage, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardsContainer.layoutManager = LinearLayoutManager(context)
        cardsContainer.adapter = cardsAdapter
    }

    override fun render(atom: StageAtom) =
            when (atom) {
                is StageAtom.Loading -> renderLoading()
                is StageAtom.Error -> renderError(atom.error)
                is StageAtom.Ready -> renderStage(atom.stage)
            }

    private fun renderLoading() {
        this.title.text = "Loading"
        this.cardsAdapter.cards = emptyList()
    }

    private fun renderError(error: Throwable) {
        Timber.e(error, "Error on Loading")
        this.title.text = error.message
        this.cardsAdapter.cards = emptyList()
    }

    private fun renderStage(stage: KanbanStage) {
        this.title.text = stage.title
        this.cardsAdapter.cards = stage.cards
    }

    inner class CardsAdapter : RecyclerView.Adapter<CardViewHolder>() {

        var cards = emptyList<KanbanCard>()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount() = cards.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
                CardViewHolder(inflate(R.layout.home_card, parent))

        override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
            val card = cards[position]
            card.title.let { holder.itemView.title.text = it }
        }
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

