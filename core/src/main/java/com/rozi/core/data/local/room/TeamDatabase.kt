package com.rozi.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rozi.core.data.local.entity.TeamEntity

@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class TeamDatabase : RoomDatabase() {
    abstract fun teamDao() : TeamDao
}