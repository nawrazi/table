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

    @Query("SELECT * FROM team")
    fun getTeams(): Flow<List<TeamEntity>>

    @Query("DELETE FROM team")
    suspend fun deleteAllTeams()
}