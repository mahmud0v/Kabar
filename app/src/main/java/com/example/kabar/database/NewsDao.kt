package com.example.kabar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kabar.model.Articles
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT *FROM Kabar")
    suspend fun getAllNews() : Flow<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articles: Articles)

    @Delete
    suspend fun deleteNews(articles: Articles)

    @Update
    suspend fun updateNews(articles: Articles)



}