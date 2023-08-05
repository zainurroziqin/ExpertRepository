package com.rozi.footballteam.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rozi.core.domain.usecase.TeamUseCase

class MainViewModel(teamUseCase: TeamUseCase) : ViewModel(){
    val teams = teamUseCase.getAllTeam().asLiveData()
}