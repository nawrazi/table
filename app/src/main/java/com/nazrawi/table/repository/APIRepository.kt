package com.nazrawi.table.repository

import com.nazrawi.table.model.Standing
import com.nazrawi.table.network.RetrofitClient
import com.nazrawi.table.service.APIService

class APIRepository {
    private val apiService = RetrofitClient
        .getClient()
        .create(APIService::class.java)

    suspend fun getTable(): List<Standing>? =
        apiService.getTable().body()?.response?.get(0)?.league?.standings?.get(0)
}