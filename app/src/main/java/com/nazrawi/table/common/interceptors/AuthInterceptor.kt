package com.nazrawi.table.common.interceptors

import com.nazrawi.table.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", "v3.football.api-sports.io")
            .addHeader("x-rapidapi-key", BuildConfig.API_KEY)

        return chain.proceed(builder.build())
    }

}