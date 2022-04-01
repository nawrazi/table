package com.nazrawi.table.repository

import android.content.Context
import com.nazrawi.table.model.Standing
import com.nazrawi.table.network.RetrofitClient
import com.nazrawi.table.service.APIService

class APIRepository(context: Context) {
    private val apiService = RetrofitClient
        .getClient(context)
        .create(APIService::class.java)

    suspend fun getTable(): List<Standing>? =
        apiService.getTable().body()?.response?.get(0)?.league?.standings?.get(0)
}