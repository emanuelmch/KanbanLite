package bill.kanban.home

import bill.kanban.atomic.AtomicView
import dagger.Component
import dagger.Module
import dagger.Provides

object HomeDependencies {
    fun setup(fragment: HomeFragment) {
        DaggerHomeComponent.builder()
                .homeModule(HomeModule(fragment))
                .build()
                .inject(fragment)
    }
}

@Component(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}

@Module
class HomeModule(private val fragment: HomeFragment) {
    @Provides
    fun provideHomeView(): AtomicView<HomeAtom> = fragment
}
