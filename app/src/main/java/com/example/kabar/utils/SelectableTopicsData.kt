package com.example.kabar.utils

import com.example.kabar.R
import com.example.kabar.model.ExploreTopic

class SelectableTopicsData {


    companion object {

        fun getTopics(): List<String> {
            return listOf(
                "Business", "Lifestyle", "Health",
                "Science", "Sport", "Technology", "Art", "International", "Fashion",
                "Politics", "National"
            )
        }

        fun getHeadlineCategories(): List<String> {
            return listOf(
                "business", "general", "health",
                "science", "sports", "technology", "entertainment"
            )
        }

        fun getExploreTopics(): List<ExploreTopic> {
            return listOf(
                ExploreTopic(R.drawable.health,R.string.health_topic, R.string.health, R.string.health_def),
                ExploreTopic(R.drawable.technology,R.string.technology_topic,R.string.technology, R.string.tech_def),
                ExploreTopic(R.drawable.art, R.string.entertainment_topic,R.string.art, R.string.art_def),
                ExploreTopic(R.drawable.politics,R.string.general_topic, R.string.politics, R.string.politics_def),
                ExploreTopic(R.drawable.sport,R.string.sports_topic, R.string.sport, R.string.sport_def),
                ExploreTopic(R.drawable.travel, R.string.entertainment_topic,R.string.travel, R.string.travel_def),
                ExploreTopic(R.drawable.money,R.string.business_topic, R.string.money, R.string.money_def)
            )
        }
    }


}