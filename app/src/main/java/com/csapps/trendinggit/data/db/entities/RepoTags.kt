package com.csapps.trendinggit.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository_tags")
data class RepoTags(
    @PrimaryKey
    @ColumnInfo(name = "tag_name")
    val tag: String
)
