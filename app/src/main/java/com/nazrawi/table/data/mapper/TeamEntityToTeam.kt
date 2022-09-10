package com.nazrawi.table.data.mapper

import com.nazrawi.table.data.local.entity.TeamEntity
import com.nazrawi.table.domain.model.Team

fun TeamEntity.toTeam(): Team =
    Team(
        id,
        name,
        logo,
        played,
        rank,
        points,
        goalsDiff
    )