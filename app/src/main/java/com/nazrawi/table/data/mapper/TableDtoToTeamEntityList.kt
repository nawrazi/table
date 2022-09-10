package com.nazrawi.table.data.mapper

import com.nazrawi.table.data.local.entity.TeamEntity
import com.nazrawi.table.data.remote.model.TableDto

fun TableDto.toTeamEntityList(): List<TeamEntity> {
    val teams = mutableListOf<TeamEntity>()

    if (response.isEmpty() || response[0].league.standings.isEmpty())
        return listOf()

    response[0].league.let { league ->
        league.standings[0].forEach {
            teams.add(
                TeamEntity(
                    it.team.id,
                    it.team.name,
                    it.team.logo,
                    it.all.played,
                    it.rank,
                    it.points,
                    it.goalsDiff,
                    league.leagueId
                )
            )
        }
    }

    return teams
}