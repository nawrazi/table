package com.nazrawi.table.data.mapper

import com.nazrawi.table.data.local.entity.TeamEntity
import com.nazrawi.table.data.remote.model.TableDto

fun TableDto.toTeamList(): List<TeamEntity> {
    val teams = mutableListOf<TeamEntity>()

    this.response[0].league.standings[0].forEach {
        teams.add(
            TeamEntity(
                it.team.id,
                it.team.name,
                it.team.logo,
                it.all.played,
                it.rank,
                it.points,
                it.goalsDiff
            )
        )
    }

    return teams
}