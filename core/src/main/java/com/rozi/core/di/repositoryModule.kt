package com.rozi.core.di

import com.rozi.core.data.local.LocalDataSource
import com.rozi.core.data.RemoteDataSource
import com.rozi.core.data.TeamRepository
import com.rozi.core.domain.repository.ITeamRepository
import com.rozi.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITeamRepository> { TeamRepository(get(), get(), get()) }
}