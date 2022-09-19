package com.example.kabar.utils

class SelectableTopicsData{


    companion object {

        fun getTopics(): List<String> {
            return listOf(
                "Business","Lifestyle","Health",
                "Science","Sport","Technology","Art","International","Fashion",
                "Politics","National"
            )
        }

        fun getHeadlineCategories():List<String>{
            return listOf(
                "business","general","health",
                "science","sports","technology","entertainment"
            )
        }
    }
}