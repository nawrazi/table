package com.nazrawi.table.data.remote.model

data class TableDto (
    val errors: Any?,
    val results: Int,
    val response: List<Response>
)

data class Response (
    val league: League
)

data class League (
    val standings: List<List<Standing>>
)

data class Standing (
    val rank: Int,
    val team: TeamDto,
    val points: Int,
    val all: All,
    val goalsDiff: Int,
)

data class TeamDto (
    val id: Long,
    val name: String,
    val logo: String
)

data class All (
    val played: Int,
)