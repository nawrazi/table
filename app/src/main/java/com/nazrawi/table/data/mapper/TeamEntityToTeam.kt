package com.nazrawi.table.data.mapper

import com.nazrawi.table.data.local.entity.TeamEntity
import com.nazrawi.table.domain.model.Team

fun TeamEntity.toTeam(): Team =
    Team(
        name = this.name,
        logo = this.logo,
        played = this.played,
        rank = this.rank,
        points = this.points,
        goalsDiff = this.goalsDiff
    )