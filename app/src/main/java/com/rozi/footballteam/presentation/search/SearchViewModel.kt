package com.rozi.footballteam.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rozi.core.domain.model.Team
import com.rozi.core.domain.usecase.TeamUseCase

class SearchViewModel(private val teamUseCase: TeamUseCase) : ViewModel() {
    fun searchTeams(query : String) : LiveData<List<Team>>{
        return teamUseCase.searchTeams(query).asLiveData()
    }
}