package bill.kanban.home

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule(private val fragment: HomeFragment) {
    @Provides
    fun provideHomeView(): HomeView = fragment
}

@Singleton
@Component(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}

object HomeDependencies {
    fun setup(fragment: HomeFragment) {
        val component = DaggerHomeComponent.builder()
                .homeModule(HomeModule(fragment))
                .build()

        component.inject(fragment)

    }
}