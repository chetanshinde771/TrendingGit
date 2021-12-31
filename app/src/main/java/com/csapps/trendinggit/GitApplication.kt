package com.csapps.trendinggit

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.csapps.trendinggit.work.SyncProjectListWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class GitApplication: Application()/*, Configuration.Provider*/ {

/*    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    private val applicationScope = CoroutineScope(Dispatchers.Default)*/

    /*override fun onCreate() {
        super.onCreate()
     //   delayedInit()
    }*/

 /*   private fun delayedInit() {
        applicationScope.launch {
            setUpRecurringWork()
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    private fun setUpRecurringWork(){
       val repeatingRequest = PeriodicWorkRequestBuilder<SyncProjectListWorker>(
            15, TimeUnit.MINUTES)
            .build()

        workManager.enqueueUniquePeriodicWork(
            SyncProjectListWorker.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }*/
}