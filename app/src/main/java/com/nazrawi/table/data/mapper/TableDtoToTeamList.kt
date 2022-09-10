package com.nazrawi.table.data.mapper

import com.nazrawi.table.data.remote.model.TableDto
import com.nazrawi.table.domain.model.Team

fun TableDto.toTeamList(): List<Team> {
    val teams = mutableListOf<Team>()

    this.response[0].league.standings[0].forEach {
        teams.add(Team(
            it.team.id,
            it.team.name,
            it.team.logo,
            it.all.played,
            it.rank,
            it.points,
            it.goalsDiff
        ))
    }

    return teams
}