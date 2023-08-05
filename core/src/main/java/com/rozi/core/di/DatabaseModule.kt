package com.rozi.core.di

import androidx.room.Room
import com.rozi.core.data.local.room.TeamDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    factory { get<TeamDatabase>().teamDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TeamDatabase::class.java, "team.db"
        ).fallbackToDestructiveMigration().build()
    }
}
