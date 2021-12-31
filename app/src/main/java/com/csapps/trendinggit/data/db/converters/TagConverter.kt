package com.csapps.trendinggit.data.db.converters

import androidx.room.TypeConverter
import com.csapps.trendinggit.data.db.entities.RepoTags
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagConverter {

    @TypeConverter
    fun tagListToJson(tagList: Array<String>): String{
        val type = object : TypeToken<Array<String>>() {}.type
        return Gson().toJson(tagList, type)
    }

    @TypeConverter
    fun tagJsonToList(tagJson: String): Array<String>{
        val type = object : TypeToken<Array<String>>() {}.type
        return Gson().fromJson(tagJson, type)
    }
}