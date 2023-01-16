package com.example.kabar.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExploreTopic")
data class ExploreTopicEntity(
    @PrimaryKey
    val title: String,
    var saved: Boolean = false
)