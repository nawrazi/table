package com.nazrawi.table.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.nazrawi.table.common.Constants
import com.nazrawi.table.common.interceptors.AuthInterceptor
import com.nazrawi.table.common.interceptors.NetworkInterceptor
import com.nazrawi.table.common.interceptors.ResponseInterceptor
import com.nazrawi.table.data.local.LocalDatabase
import com.nazrawi.table.data.local.dao.TeamDao
import com.nazrawi.table.data.remote.api.TableService
import com.nazrawi.table.domain.repository.TableRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val networkInterceptor = NetworkInterceptor(context)
        val responseInterceptor = ResponseInterceptor()
        val authInterceptor = AuthInterceptor()
        val loggingInterceptor = HttpLoggingInterceptor().also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(responseInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, oktHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(oktHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTableService(retrofit: Retrofit): TableService {
        return retrofit.create(TableService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryDao(database: LocalDatabase): TeamDao {
        return database.teamDao()
    }

    @Provides
    @Singleton
    fun provideTableRepository(
        tableService: TableService,
        teamDao: TeamDao,
        localDatabase: LocalDatabase
    ): TableRepository {
        return TableRepository(tableService, teamDao, localDatabase)
    }

}