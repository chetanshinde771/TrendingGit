package com.csapps.trendinggit.data.db

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.csapps.trendinggit.data.db.dao.RepoListDao
import com.csapps.trendinggit.data.db.entities.RepoDetails
import com.csapps.trendinggit.data.db.entities.RepoTags
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDaoTest {

   /* @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()*/

    private lateinit var repoDatabase: GitRepoDatabase
    private lateinit var repoListDao: RepoListDao

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        repoDatabase = Room.inMemoryDatabaseBuilder(context,
            GitRepoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repoListDao = repoDatabase.getRepoListDao()
    }

    @After
    fun tearDown(){
        repoDatabase.close()
    }

    @Test
    fun addRepo() {
        runBlocking {
            val tagList = ArrayList<RepoTags>()
            tagList.apply {
                add(RepoTags(tag = "android"))
                add(RepoTags(tag = "architecture"))
                add(RepoTags(tag = "kotlin"))
            }
            val repoDetails = RepoDetails(
                1, "chetanshinde771",
                "android projects on architecture", "www.google.com",
                "updated on 15 Oct 21", 200/*, tagList*/
            )
            val repoDetails1 = RepoDetails(
                2, "mvvm",
                "android projects on mvvm", "www.google.com",
                "updated on 22 Nov 21", 200/*, tagList*/
            )
            repoListDao.addGitRepo(repoDetails)
            repoListDao.addGitRepo(repoDetails1)
            repoListDao.deleteRepoList()

            val dbList = repoListDao.fetchGitRepoList().getOrAwaitValue()

            Log.d("size:",  dbList.size.toString())
            assertThat(dbList.contains(repoDetails)).isTrue()
        }
    }

}