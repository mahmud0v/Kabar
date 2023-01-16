package com.example.kabar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kabar.model.ExploreTopicEntity

@Dao
interface ExploreDao {

    @Query("SELECT *FROM ExploreTopic")
    suspend fun getAllTopics(): List<ExploreTopicEntity>?


    @Query("SELECT *FROM ExploreTopic where title = :title")
    suspend fun getTopic(title: String): ExploreTopicEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: ExploreTopicEntity)

    @Update
    suspend fun updateTopic(updateTopic: ExploreTopicEntity)


}