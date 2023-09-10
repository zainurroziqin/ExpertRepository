package com.rozi.core.di

import androidx.room.Room
import com.rozi.core.data.local.room.TeamDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    factory { get<TeamDatabase>().teamDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rozi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TeamDatabase::class.java, "team.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}
