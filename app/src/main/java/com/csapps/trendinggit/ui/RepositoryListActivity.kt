package com.csapps.trendinggit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.csapps.trendinggit.R
import com.csapps.trendinggit.data.remote.GitRepoListResponse
import com.csapps.trendinggit.ui.adapter.RepositoryListAdapter
import com.csapps.trendinggit.util.Resource
import com.csapps.trendinggit.viewmodel.ProjectListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_repository_list.*

@AndroidEntryPoint
class RepositoryListActivity : AppCompatActivity() {

    private val projectListViewModel by viewModels<ProjectListViewModel>()
    private var listAdapter: RepositoryListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        projectListViewModel.retrieveProjectList()

        projectListViewModel.repoData.observe(this, Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressDialog()
                    updateList(response.data)
                }
                is Resource.Error ->{
                    hideProgressDialog()
                    Toast.makeText(this@RepositoryListActivity, response.msg, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading ->{
                    showProgressDialog()
                }
            }
        })

        /*projectListViewModel.projectList.observe(this, Observer {
            println("data available:  ${it.size}")
        })*/
    }


    private fun showProgressDialog(){
        roundProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressDialog(){
        roundProgressBar.visibility = View.GONE
    }

    private fun updateList(projectList: GitRepoListResponse?) {
        listAdapter = RepositoryListAdapter(projectList!!.articles)
        projectListRecycler.apply{
            adapter = listAdapter
            layoutManager = LinearLayoutManager(
                this@RepositoryListActivity, LinearLayoutManager.VERTICAL, false)
        }


    }

   /* fun startRunBlocking(){

        println("Before coroutine")
       // runBlocking {
            CoroutineScope(Dispatchers.Main).launch{
                delay(3000)
                println("Inside coroutine")
            }
       // }
        println("After coroutine")
    }*/
}