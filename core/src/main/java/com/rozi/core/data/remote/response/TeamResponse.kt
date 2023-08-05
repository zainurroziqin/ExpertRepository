package com.rozi.core.data.remote.response

data class ListTeamResponse(
    val teams: List<TeamsResponse>
)

data class TeamsResponse(
    val strTeamFanart1 : String,
    val strInstagram: String,
    val idTeam: String,
    val strDescriptionEN: String,
    val strWebsite: String,
    val strYoutube: String,
    val strTeam: String,
    val strTwitter: String,
    val strStadium: String,
)

