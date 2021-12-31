package com.csapps.trendinggit.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csapps.trendinggit.data.db.entities.RepoDetails

@Dao
interface RepoListDao {

    @Query("SELECT * FROM repository_details")
    fun fetchGitRepoList(): LiveData<List<RepoDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGitRepoList(repoList: List<RepoDetails>)

    @Insert
    suspend fun addGitRepo(repoList: RepoDetails)

    @Query("DELETE FROM repository_details")
    suspend fun deleteRepoList()
}
