package com.csapps.trendinggit.data.db.entities

import androidx.room.*
import com.csapps.trendinggit.data.db.converters.TagConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repository_details")
data class RepoDetails(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "repo_id")
    @SerializedName("id")
    val repoId: Long,

    @ColumnInfo(name = "repo_name")
    @SerializedName("full_name")
    val repoName: String,

    @ColumnInfo(name = "repo_desc")
    @SerializedName("description")
    val repoDesc: String,

    @ColumnInfo(name = "repo_url")
    @SerializedName("url")
    val repoUrl: String,

    @ColumnInfo(name = "repo_updated_date")
    @SerializedName("updated_at")
    val repoUpdatedDate: String,

    @ColumnInfo(name = "repo_star_count")
    @SerializedName("stargazers_count")
    val repoStarCount: Int,

    /*@ColumnInfo(name = "repo_tags")
    @SerializedName("topics")
    var repoTags: Array<String>? = null*/
)
