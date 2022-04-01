package com.nazrawi.table.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://v3.football.api-sports.io/"
    const val API_KEY = "6ae023583d86e07cb772197146f6d837"

    fun getClient(context: Context): Retrofit {
        val oktHttpClient = OkHttpClient.Builder()
//            .addInterceptor()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oktHttpClient.build())
            .build()
    }
}