package com.example.kabar.remote

import com.example.kabar.database.NewsDao
import com.example.kabar.model.Articles
import javax.inject.Inject

class DataBaseRemoteDataSource @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun getAllNews() = newsDao.getAllNews()

    suspend fun insertNews(articles: Articles) = newsDao.insertNews(articles)

    suspend fun deleteNews(articles: Articles) = newsDao.deleteNews(articles)

    suspend fun updateNews(articles: Articles) = newsDao.updateNews(articles)


}