package com.csapps.trendinggit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csapps.trendinggit.AppConstants
import com.csapps.trendinggit.data.remote.GitRepoListResponse
import com.csapps.trendinggit.data.repository.ProjectListRepository
import com.csapps.trendinggit.data.repository.ProjectListRepositoryImpl
import com.csapps.trendinggit.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(
   val  projectListRepository: ProjectListRepositoryImpl): ViewModel(){

    var pgNo: Int = 0
    var repoData: MutableLiveData<Resource<GitRepoListResponse>> = MutableLiveData()

    init {
        pgNo=0
    }

   fun retrieveProjectList(){
       /*launching a coroutine to handle api call,
       * using viewmodelScope so that the api call life exist only till viewmodel life span*/
       viewModelScope.launch{
           /*before calling api setting the livedata status to loading to show progress bar in view*/
           repoData.postValue(Resource.Loading())
           /*actual api call, and getting its response*/
           var repoResponse = projectListRepository.fetchTrendingAndroidRepo(getApiCallRequestMap())
           /*updating livedata status to api response status*/
           repoData.postValue(handleResponse(repoResponse))
           pgNo++
       }
   }

    private fun handleResponse(repoResponse: Response<GitRepoListResponse>): Resource<GitRepoListResponse> {
        if(repoResponse.isSuccessful){
            repoResponse.body()?.let { gitResponse ->
                return Resource.Success(gitResponse)
            }?:    return Resource.Error("Something went wrong")
        }
        else
            return Resource.Error("Something went wrong")
    }


    private fun getApiCallRequestMap(): Map<String, String> {
        val reqMap = HashMap<String, String>()
        reqMap.apply {
            put(AppConstants.SORT_FILTER, "stars")
            put(AppConstants.QUERY_FILTER, "android in:name")
            put(AppConstants.PAGE_NO, pgNo.toString())
        }

        return reqMap
    }
}