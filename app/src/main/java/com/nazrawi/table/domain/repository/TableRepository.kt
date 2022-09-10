package com.nazrawi.table.domain.repository

import com.nazrawi.table.data.remote.model.Standing
import com.nazrawi.table.data.remote.api.TableService
import javax.inject.Inject

class TableRepository @Inject constructor(
    private val tableService: TableService
) {
    suspend fun getTable(): List<Standing>? =
        tableService.getTable().body()?.response?.get(0)?.league?.standings?.get(0)
}