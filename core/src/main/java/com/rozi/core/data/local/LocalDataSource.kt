package com.rozi.core.data.local

import com.rozi.core.data.local.entity.TeamEntity
import com.rozi.core.data.local.room.TeamDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val teamDao: TeamDao) {

    fun getAllTeams() : Flow<List<TeamEntity>> = teamDao.getAllTourism()

    fun getFavoriteTeams() : Flow<List<TeamEntity>> = teamDao.getFavoriteTeams()

    fun searchTeams(query : String) : Flow<List<TeamEntity>> = teamDao.searchTeams(query)

    suspend fun insertTeams(teamList : List<TeamEntity>) = teamDao.insertTeams(teamList)

    fun setFavoriteTeam(team: TeamEntity, newState : Boolean){
        team.isFavorite = newState
        teamDao.updateFavoriteTeams(team)
    }
}