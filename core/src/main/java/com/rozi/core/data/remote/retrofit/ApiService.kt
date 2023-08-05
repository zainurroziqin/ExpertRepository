package com.rozi.core.data.remote.retrofit

import com.rozi.core.data.remote.response.ListTeamResponse
import com.rozi.core.data.remote.response.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search_all_teams.php?l=English%20Premier%20League")
    suspend fun getList() : ListTeamResponse

    @GET("searchteams.php")
    suspend fun getFavorite(
        @Query("t") t : String
    ) : TeamsResponse
}