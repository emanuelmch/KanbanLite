package bill.kanban.home.stages

import bill.kanban.atomic.AtomicView
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

object StageDependencies {

    const val STAGE_ID = "stageId"

    fun setup(fragment: StageFragment, stageId: Int) {
        DaggerStageComponent.builder()
                .stageModule(StageModule(fragment, stageId))
                .build()
                .inject(fragment)
    }
}

@Component(modules = arrayOf(StageModule::class))
interface StageComponent {
    fun inject(fragment: StageFragment)
}

@Module
class StageModule(val fragment: StageFragment, val stageId: Int) {
    @Provides fun stageView(): AtomicView<StageAtom> = fragment
    @Provides @Named(StageDependencies.STAGE_ID) fun stageId() = stageId
}