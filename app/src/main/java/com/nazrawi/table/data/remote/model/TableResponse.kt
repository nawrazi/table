package com.nazrawi.table.data.remote.model

data class TableResponse(
    val status: Int,
    val message: String,
    val data: TableDto
)

data class TableDto(
    val table: List<TeamDto>
)

data class TeamDto(
    val id: String,
    val rank: Int,
    val name: String,
    val points: Int,
    val goals: String,
    val matches: Int,
    val logo: String,
    val leagueId: Int
)