package com.example.kabar.database

import androidx.room.*
import com.example.kabar.model.Articles

@Dao
interface NewsDao {

    @Query("SELECT *FROM Kabar")
    suspend fun getAllNews(): List<Articles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articles: Articles)

    @Query("Delete from Kabar where id=:id")
    suspend fun deleteNews(id: Int)

    @Update
    suspend fun updateNews(articles: Articles)

    @Query("SELECT *FROM Kabar WHERE title =:title")
    suspend fun checkArticle(title: String): Articles?


}