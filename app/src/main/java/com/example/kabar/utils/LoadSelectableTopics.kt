package com.example.kabar.utils

class LoadSelectableTopics {


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