package com.rozi.core.utils

import com.rozi.core.data.local.entity.TeamEntity
import com.rozi.core.data.remote.response.TeamsResponse
import com.rozi.core.domain.model.Team

object DataMapper {
    fun mapResponseToEntities(input: List<TeamsResponse>): List<TeamEntity> {
        val teamList = ArrayList<TeamEntity>()
        input.map {
            val team = TeamEntity(
                teamsId = it.idTeam,
                teamFanart1 = it.strTeamFanart1,
                instagram = it.strInstagram,
                descriptionEN = it.strDescriptionEN,
                website = it.strWebsite,
                youtube = it.strYoutube,
                team = it.strTeam,
                twitter = it.strTwitter,
                stadium = it.strStadium,
                isFavorite = false
            )
            teamList.add(team)
        }
        return teamList
    }

    fun mapEntitiesToDomain(input: List<TeamEntity>): List<Team> =
        input.map {
            Team(
                teamsId = it.teamsId,
                teamFanart1 = it.teamFanart1,
                instagram = it.instagram,
                descriptionEN = it.descriptionEN,
                website = it.website,
                youtube = it.youtube,
                team = it.team,
                twitter = it.twitter,
                stadium = it.stadium,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Team) = TeamEntity(
        teamsId = input.teamsId,
        teamFanart1 = input.teamFanart1,
        instagram = input.instagram,
        descriptionEN = input.descriptionEN,
        website = input.website,
        youtube = input.youtube,
        team = input.team,
        twitter = input.twitter,
        stadium = input.stadium,
        isFavorite = input.isFavorite
    )

}