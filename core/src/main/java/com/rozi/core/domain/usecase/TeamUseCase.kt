package com.rozi.core.domain.usecase

import com.rozi.core.data.Resource
import com.rozi.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamUseCase {
    fun getAllTeam() : Flow<Resource<List<Team>>>

    fun getFavoriteTeam() : Flow<List<Team>>

    fun searchTeams(query : String) : Flow<List<Team>>

    fun setFavoriteTeam(team: Team, state: Boolean)
}