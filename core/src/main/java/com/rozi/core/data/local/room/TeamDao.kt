package com.rozi.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rozi.core.data.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getAllTourism() : Flow<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE isFavorite = 1")
    fun getFavoriteTeams() : Flow<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE team LIKE '%' ||:like || '%'")
    fun searchTeams(like : String) : Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams : List<TeamEntity>)

    @Update
    fun updateFavoriteTeams(team : TeamEntity)
}