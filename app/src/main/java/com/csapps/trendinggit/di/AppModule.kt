package com.csapps.trendinggit.di

import android.content.Context
import android.content.SharedPreferences
import androidx.work.WorkManager
import com.csapps.trendinggit.AppConstants
import com.csapps.trendinggit.AppConstants.PAGE_NO
import com.csapps.trendinggit.AppConstants.QUERY_FILTER
import com.csapps.trendinggit.AppConstants.SHARED_PREFS
import com.csapps.trendinggit.AppConstants.SORT_FILTER
import com.csapps.trendinggit.data.db.GitRepoDatabase
import com.csapps.trendinggit.data.db.dao.RepoListDao
import com.csapps.trendinggit.data.remote.ApiInterFace
import com.csapps.trendinggit.data.repository.ProjectListRepositoryImpl
import com.csapps.trendinggit.util.DispatcherProvider
import com.csapps.trendinggit.util.NetworkMonitorUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGitDatabase(
        @ApplicationContext context: Context): GitRepoDatabase{
        return GitRepoDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideRepoListDao(gitDatabase: GitRepoDatabase): RepoListDao{
        return gitDatabase.getRepoListDao()
    }

    /*@Singleton
    @Provides
    fun provideApiRequestMap(
        @Named("reqPageNum") pg: Int): Map<String, String>{
        val reqMap = HashMap<String, String>()
        reqMap.apply{
            put(SORT_FILTER, "stars")
            put(QUERY_FILTER, "android in:name")
            put(PAGE_NO, "2")
        }
        return reqMap
    }*/

    @Singleton
    @Provides
    fun provideInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(
        interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit): ApiInterFace {
        return retrofit.create(ApiInterFace::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext context: Context): NetworkMonitorUtil {
        return NetworkMonitorUtil(context)
    }

    @Singleton
    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)


    @Singleton
    @Provides
    fun provideSharedPrefEditor(
        sharedPreferences: SharedPreferences): SharedPreferences.Editor =
        sharedPreferences.edit()

   /* @Singleton
    @Provides
    @Named("reqPageNum")
    fun providePageNumber(sharedPreferences: SharedPreferences) =
        sharedPreferences.getInt(PAGE_NO, 0)*/

    @Singleton
    @Provides
    fun provideWorkManager(
        @ApplicationContext context: Context): WorkManager{
        return WorkManager.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideProjectListRepository(repoListDao: RepoListDao,
                                     apiService: ApiInterFace/*, map: Map<String, String>,
    sharedPrefEditor: SharedPreferences.Editor,
    @Named("reqPageNum") pg: Int */): ProjectListRepositoryImpl{
        return ProjectListRepositoryImpl(repoListDao, apiService)
    }

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider =
        object : DispatcherProvider{
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined

        }

}