package com.nazrawi.table.data.mapper

import com.nazrawi.table.data.local.entity.TeamEntity
import com.nazrawi.table.data.remote.model.TeamDto

fun TeamDto.toTeamEntity(): TeamEntity =
    TeamEntity(
        id = this.id,
        name = this.name,
        logo = this.logo,
        played = this.matches,
        rank = this.rank,
        points = this.points,
        goalsDiff = this.goals,
        leagueId = this.leagueId
    )