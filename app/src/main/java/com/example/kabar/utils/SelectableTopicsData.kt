package com.example.kabar.utils

class SelectableTopicsData{


    companion object {

        fun getTopics(): List<String> {
            return listOf(
                "National", "Health", "Sport", "Lifestyle",
                "International", "Fashion", "Business", "Art", "Technology", "Science",
                "Politics"
            )
        }
    }
}