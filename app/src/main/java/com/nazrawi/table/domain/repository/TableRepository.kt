package com.nazrawi.table.domain.repository

import com.nazrawi.table.data.mapper.toTeamList
import com.nazrawi.table.data.remote.api.TableService
import com.nazrawi.table.domain.model.Team
import javax.inject.Inject

class TableRepository @Inject constructor(
    private val tableService: TableService
) {
    suspend fun getTable(): List<Team> =
        tableService.getTable().body()!!.toTeamList()
}