package com.rozi.core.data

import com.rozi.core.data.local.LocalDataSource
import com.rozi.core.data.remote.NetworkBoundResource
import com.rozi.core.data.remote.response.ApiResponse
import com.rozi.core.data.remote.response.TeamsResponse
import com.rozi.core.domain.model.Team
import com.rozi.core.domain.repository.ITeamRepository
import com.rozi.core.utils.AppExecutors
import com.rozi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITeamRepository {
    override fun getAllTeam(): Flow<Resource<List<Team>>> =
        object : NetworkBoundResource<List<Team>, List<TeamsResponse>>() {
            override fun loadFromDB(): Flow<List<Team>> {
                return localDataSource.getAllTeams().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TeamsResponse>>> =
                remoteDataSource.getAllTeam()


            override suspend fun saveCallResult(data: List<TeamsResponse>) {
                val teamList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertTeams(teamList)
            }

            override fun shouldFetch(data: List<Team>?): Boolean =
                data == null || data.isEmpty()

        }.asFlow()

    override fun getFavoriteTeam(): Flow<List<Team>> {
        return localDataSource.getFavoriteTeams().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun searchTeams(query: String): Flow<List<Team>> {
        return localDataSource.searchTeams(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapper.mapDomainToEntity(team)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTeam(teamEntity, state) }
    }
}