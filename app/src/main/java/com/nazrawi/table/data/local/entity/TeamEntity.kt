package com.nazrawi.table.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val logo: String,
    val played: Int,
    val rank: Int,
    val points: Int,
    val goalsDiff: Int
)