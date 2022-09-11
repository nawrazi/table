package com.nazrawi.table.domain.repository

import androidx.room.withTransaction
import com.nazrawi.table.common.resource.Resource
import com.nazrawi.table.common.resource.networkBoundResource
import com.nazrawi.table.data.local.LocalDatabase
import com.nazrawi.table.data.local.dao.TeamDao
import com.nazrawi.table.data.mapper.toTeam
import com.nazrawi.table.data.mapper.toTeamEntityList
import com.nazrawi.table.data.remote.api.TableService
import com.nazrawi.table.domain.model.League
import com.nazrawi.table.domain.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TableRepository @Inject constructor(
    private val tableService: TableService,
    private val teamDao: TeamDao,
    private val localDatabase: LocalDatabase
) {
    suspend fun getTable(league: League): Flow<Resource<List<Team>>> {
        val result = networkBoundResource(
            query = { teamDao.getTeams(league.id) },
            fetch = { tableService.getTable(league.id.toString()).body()!! },
            saveFetchResult = {
                localDatabase.withTransaction {
                    teamDao.deleteAllTeams(league.id)
                    teamDao.insertTeams(it.toTeamEntityList())
                }
            },
            isEmpty = { it.isEmpty() }
        )

        return result.map {
            when (it) {
                is Resource.Loading -> {
                    Resource.Loading()
                }
                is Resource.Success -> {
                    Resource.Success(it.value!!.map { teamEntity -> teamEntity.toTeam() })
                }
                is Resource.Error -> {
                    Resource.Error(
                        it.message,
                        it.value!!.map { teamEntity -> teamEntity.toTeam() }
                    )
                }
            }
        }
    }
}