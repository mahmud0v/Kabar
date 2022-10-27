package com.example.kabar.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsResponse(
    val id: Int,
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)