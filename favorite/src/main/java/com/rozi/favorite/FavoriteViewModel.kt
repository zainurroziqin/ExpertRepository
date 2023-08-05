package com.rozi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rozi.core.domain.usecase.TeamUseCase

class FavoriteViewModel(teamUseCase: TeamUseCase) : ViewModel() {
    val favoriteTeam = teamUseCase.getFavoriteTeam().asLiveData()
}