package com.example.kabar.repository

import com.example.kabar.model.ExploreTopicEntity
import com.example.kabar.remote.ExploreRemoteDataSource
import javax.inject.Inject

class ExploreTopicRepository @Inject constructor(
    private val exploreRemoteDataSource: ExploreRemoteDataSource
) {
    suspend fun getAllTopics() = exploreRemoteDataSource.getAllTopics()

    suspend fun getTopic(title: String) = exploreRemoteDataSource.getTopic(title)

    suspend fun insertTopic(topic: ExploreTopicEntity) = exploreRemoteDataSource.insertTopic(topic)

    suspend fun updateTopic(topic: ExploreTopicEntity) = exploreRemoteDataSource.updateTopic(topic)


}