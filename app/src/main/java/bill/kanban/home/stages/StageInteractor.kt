package bill.kanban.home.stages

import bill.kanban.infra.asSingle
import javax.inject.Inject

class StageInteractor @Inject constructor() {

    fun getKanbanStageById(stageId: Int) =
            when (stageId) {
                1 -> KanbanStage("To Do", listOf(KanbanCard("To Do 1"), KanbanCard("To Do 2")))
                2 -> KanbanStage("Doing", listOf(KanbanCard("Doing 1")))
                3 -> KanbanStage("Done", listOf(KanbanCard("Done 1"), KanbanCard("Done 2"), KanbanCard("Done 3")))
                else -> throw IllegalArgumentException("Unknown StageId $stageId")
            }.asSingle()
}
