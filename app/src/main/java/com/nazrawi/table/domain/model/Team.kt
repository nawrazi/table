package com.nazrawi.table.domain.model

data class Team(
    val id: Long,
    val name: String,
    val logo: String,
    val played: Int,
    val rank: Int,
    val points: Int,
    val goalsDiff: Int
)