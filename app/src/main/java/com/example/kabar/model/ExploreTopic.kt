package com.example.kabar.model

data class ExploreTopic(
    val imgRes: Int,
    val category:Int,
    val title: Int,
    val desc: Int,
    var click: Boolean = false
)