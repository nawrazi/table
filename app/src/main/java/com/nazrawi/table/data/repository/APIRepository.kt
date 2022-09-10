package com.nazrawi.table.data.repository

import com.nazrawi.table.data.remote.model.Standing
import com.nazrawi.table.common.RetrofitClient
import com.nazrawi.table.data.remote.api.TableService

class APIRepository {
    private val tableService = RetrofitClient
        .getClient()
        .create(TableService::class.java)

    suspend fun getTable(): List<Standing>? =
        tableService.getTable().body()?.response?.get(0)?.league?.standings?.get(0)
}