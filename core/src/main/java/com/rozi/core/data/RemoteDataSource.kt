package com.rozi.core.data

import com.rozi.core.data.remote.response.ApiResponse
import com.rozi.core.data.remote.response.TeamsResponse
import com.rozi.core.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllTeam() : Flow<ApiResponse<List<TeamsResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.teams
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.teams))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}