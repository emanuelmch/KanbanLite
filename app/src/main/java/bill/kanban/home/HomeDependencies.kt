package bill.kanban.home

import dagger.Component

object HomeDependencies {
    fun setup(fragment: HomeFragment) {
        DaggerHomeComponent.builder()
                .build()
                .inject(fragment)
    }
}

@Component
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}
