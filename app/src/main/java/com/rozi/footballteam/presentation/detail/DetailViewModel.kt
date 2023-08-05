package com.rozi.footballteam.presentation.detail

import androidx.lifecycle.ViewModel
import com.rozi.core.domain.model.Team
import com.rozi.core.domain.usecase.TeamUseCase

class DetailViewModel(private val teamUseCase: TeamUseCase) : ViewModel(){
    fun setFavoriteTeam(team: Team, newStatus: Boolean){
        teamUseCase.setFavoriteTeam(team,newStatus)
    }
}