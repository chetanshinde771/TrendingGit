package com.csapps.trendinggit.data.remote

import com.csapps.trendinggit.data.db.entities.RepoDetails
import com.google.gson.annotations.SerializedName

data class GitRepoListResponse(
    @SerializedName("total_count") val totalCount : Long,
    @SerializedName("items") val articles : List<RepoDetails>
)
