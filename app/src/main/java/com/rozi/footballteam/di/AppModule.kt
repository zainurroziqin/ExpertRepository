package com.rozi.footballteam.di

import com.rozi.core.domain.usecase.TeamInteractor
import com.rozi.core.domain.usecase.TeamUseCase
import com.rozi.footballteam.presentation.detail.DetailViewModel
import com.rozi.footballteam.presentation.main.MainViewModel
import com.rozi.footballteam.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TeamUseCase> { TeamInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}