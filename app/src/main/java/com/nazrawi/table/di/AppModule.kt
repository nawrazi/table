package com.nazrawi.table.di

import android.content.Context
import com.google.gson.Gson
import com.nazrawi.table.common.Constants
import com.nazrawi.table.common.interceptors.AuthInterceptor
import com.nazrawi.table.common.interceptors.NetworkInterceptor
import com.nazrawi.table.common.interceptors.ResponseInterceptor
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
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(responseInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(logger)
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

    @Provides
    @Singleton
    fun provideTableService(retrofit: Retrofit): TableService {
        return retrofit.create(TableService::class.java)
    }

    @Provides
    @Singleton
    fun provideTableRepository(tableService: TableService): TableRepository {
        return TableRepository(tableService)
    }

}