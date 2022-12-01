package com.nazrawi.table.domain.model

data class Team(
    val name: String,
    val logo: String,
    val played: Int,
    val rank: Int,
    val points: Int,
    val goalsDiff: String
)