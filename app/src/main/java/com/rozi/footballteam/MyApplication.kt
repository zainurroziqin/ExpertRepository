package com.rozi.footballteam

import android.app.Application
import com.rozi.core.di.databaseModule
import com.rozi.core.di.networkModule
import com.rozi.core.di.repositoryModule
import com.rozi.footballteam.di.useCaseModule
import com.rozi.footballteam.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}