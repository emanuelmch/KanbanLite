package bill.kanban

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class KanbanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}