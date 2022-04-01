package com.nazrawi.table.model

data class APIResponse (
    val errors: Any?,
    val response: List<Response>
)

data class Response (
    val league: League
)

data class League (
    val standings: List<List<Standing>>
)

data class Standing (
    val rank: Long,
    val team: Team,
    val points: Long,
    val all: All,
    val goalsDiff: Long,
)

data class Team (
    val id: Long,
    val name: String,
    val logo: String
)

data class All (
    val played: Long,
)