package com.csapps.trendinggit.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.csapps.trendinggit.AppConstants
import com.csapps.trendinggit.data.db.dao.RepoListDao
import com.csapps.trendinggit.data.db.entities.RepoDetails
import com.csapps.trendinggit.data.remote.ApiInterFace
import com.csapps.trendinggit.data.remote.GitRepoListResponse
import com.csapps.trendinggit.util.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ProjectListRepositoryImpl@Inject constructor(
    private val repoListDao: RepoListDao,
    private val apiService: ApiInterFace,
    /*private val sharedPrefEditor: SharedPreferences.Editor,
    private var pageNum: Int*/): ProjectListRepository{

    /*live data to observe the changes in the database*/
    val projectList: LiveData<List<RepoDetails>> = repoListDao.fetchGitRepoList()

    override suspend fun insertGitRepos(repoList: List<RepoDetails>) {
        repoListDao.addGitRepoList(repoList)
    }

    override suspend fun deleteAllGitRepos() {
        repoListDao.deleteRepoList()
    }

    override suspend fun fetchTrendingAndroidRepo(map: Map<String, String>) =
        apiService.fetchTopAndroidLibraries(map)

}