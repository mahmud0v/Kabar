package com.example.kabar.model


data class NewsResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<Articles>
)