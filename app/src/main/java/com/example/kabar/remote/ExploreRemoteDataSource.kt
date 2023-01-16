package com.example.kabar.remote

import com.example.kabar.database.ExploreDao
import com.example.kabar.model.ExploreTopicEntity
import javax.inject.Inject


class ExploreRemoteDataSource @Inject constructor(
    private val exploreDao: ExploreDao
) {

    suspend fun getAllTopics() = exploreDao.getAllTopics()


    suspend fun getTopic(title: String) = exploreDao.getTopic(title)


    suspend fun insertTopic(topic: ExploreTopicEntity) = exploreDao.insertTopic(topic)


    suspend fun updateTopic(topic: ExploreTopicEntity) = exploreDao.updateTopic(topic)


}