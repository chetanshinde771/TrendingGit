package com.csapps.trendinggit.data.remote

import com.csapps.trendinggit.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/*specify api endpoints here*/
interface ApiInterFace {

    /*api call to fetch the top trending android libraries*/
    @GET("search/repositories")
    suspend fun fetchTopAndroidLibraries(
        @QueryMap requestMap: Map<String, String>): Response<GitRepoListResponse>
}