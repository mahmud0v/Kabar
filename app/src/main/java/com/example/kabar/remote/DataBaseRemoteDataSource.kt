package com.example.kabar.remote

import com.example.kabar.database.NewsDao
import com.example.kabar.model.Articles
import javax.inject.Inject

class DataBaseRemoteDataSource @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun getAllNews() = newsDao.getAllNews()

    suspend fun insertNews(articles: Articles) = newsDao.insertNews(articles)

    suspend fun deleteNews(title: String) = newsDao.deleteNews(title)

    suspend fun updateNews(articles: Articles) = newsDao.updateNews(articles)

    suspend fun checkArticle(title: String) = newsDao.checkArticle(title)


}