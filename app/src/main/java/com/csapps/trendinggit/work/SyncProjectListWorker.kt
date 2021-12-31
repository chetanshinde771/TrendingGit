package com.csapps.trendinggit.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.csapps.trendinggit.data.repository.ProjectListRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/*@HiltWorker*/
class SyncProjectListWorker /*@AssistedInject constructor*/(
 /*   @Assisted context: Context, @Assisted params: WorkerParameters,
    private val projectListRepository: ProjectListRepositoryImpl*/)/*: CoroutineWorker(context, params)*/ {


    companion object{
        const val WORK_NAME = "com.csapps.trendinggit.work.SyncProjectListWorker"
    }

    /*override suspend fun doWork(): Result  =

            withContext(Dispatchers.IO) {

                try{
                val result = projectListRepository.fetchTrendingAndroidRepo()
                if(result.data!=null){
                    projectListRepository.insertGitRepos(result.data.articles)
                    Result.success()
                }else
                    Result.failure()

                }catch (e: Exception){
                    Result.retry()
                }
            }*/

}