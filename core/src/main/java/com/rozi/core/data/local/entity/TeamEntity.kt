package com.rozi.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity (
     @PrimaryKey
     @ColumnInfo(name = "teamsId")
     var teamsId: String,
     @ColumnInfo(name = "teamFanArt1")
     var teamFanart1 : String,
     @ColumnInfo(name = "instagram")
     var instagram: String,
     @ColumnInfo(name = "description")
     var descriptionEN: String,
     @ColumnInfo(name = "website")
     var website: String,
     @ColumnInfo(name = "youtube")
     var youtube: String,
     @ColumnInfo(name = "team")
     var team: String,
     @ColumnInfo(name = "twitter")
     var twitter: String,
     @ColumnInfo(name = "stadium")
     var stadium: String,
     @ColumnInfo(name = "isFavorite")
     var isFavorite: Boolean = false,

)