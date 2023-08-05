package com.rozi.core.domain.usecase

import com.rozi.core.domain.model.Team
import com.rozi.core.domain.repository.ITeamRepository

class TeamInteractor(private val teamRepository: ITeamRepository) : TeamUseCase {
    override fun getAllTeam() = teamRepository.getAllTeam()

    override fun getFavoriteTeam() = teamRepository.getFavoriteTeam()

    override fun searchTeams(query: String) = teamRepository.searchTeams(query)

    override fun setFavoriteTeam(team: Team, state: Boolean)  = teamRepository.setFavoriteTeam(team, state)
}