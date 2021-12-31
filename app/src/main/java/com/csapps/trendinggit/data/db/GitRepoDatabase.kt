package com.csapps.trendinggit.data.db

import android.content.Context
import androidx.room.*
import com.csapps.trendinggit.AppConstants
import com.csapps.trendinggit.data.db.GitRepoDatabase.Companion.DB_VERSION
import com.csapps.trendinggit.data.db.converters.TagConverter
import com.csapps.trendinggit.data.db.dao.RepoListDao
import com.csapps.trendinggit.data.db.entities.RepoDetails
import com.csapps.trendinggit.data.db.entities.RepoTags

@Database(entities = [RepoDetails::class], version = DB_VERSION)
@TypeConverters(TagConverter::class)
abstract class GitRepoDatabase: RoomDatabase() {

    abstract fun getRepoListDao(): RepoListDao

    companion object{
        const val DB_VERSION: Int = 1
        private const val DB_NAME: String = "git_database"

        @Volatile
        private var INSTANCE: GitRepoDatabase? = null

        fun getDatabase(context: Context): GitRepoDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, GitRepoDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

}