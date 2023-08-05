package com.rozi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val teamsId: String,
    val teamFanart1 : String,
    val instagram: String,
    val descriptionEN: String,
    val website: String,
    val youtube: String,
    val team: String,
    val twitter: String,
    val stadium: String,
    val isFavorite: Boolean,
) : Parcelable
