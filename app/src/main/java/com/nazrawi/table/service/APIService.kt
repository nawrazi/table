package com.nazrawi.table.service

import com.nazrawi.table.model.APIResponse
import com.nazrawi.table.network.RetrofitClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {

    @Headers(
        "x-rapidapi-host: v3.football.api-sports.io",
        "x-rapidapi-key: ${RetrofitClient.API_KEY}"
    )
    @GET("standings?league=39&season=2021")
    suspend fun getTable(): Response<APIResponse>

}