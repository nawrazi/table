package com.nazrawi.table.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazrawi.table.data.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamEntity>)

    @Query("SELECT * FROM team WHERE leagueId = :id")
    fun getTeams(id: Int): Flow<List<TeamEntity>>

    @Query("DELETE FROM team WHERE leagueId = :id")
    suspend fun deleteAllTeams(id: Int)
}