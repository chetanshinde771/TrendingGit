package com.csapps.trendinggit.data.repository


import androidx.lifecycle.LiveData
import com.csapps.trendinggit.data.db.entities.RepoDetails
import com.csapps.trendinggit.data.remote.GitRepoListResponse
import com.csapps.trendinggit.util.Resource
import retrofit2.Response

interface ProjectListRepository{

    suspend fun insertGitRepos(repoList: List<RepoDetails>)

    suspend fun deleteAllGitRepos()

    suspend fun fetchTrendingAndroidRepo(  map: Map<String, String>): Response<GitRepoListResponse>
}