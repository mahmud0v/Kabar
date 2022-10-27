package com.example.kabar.repository

import com.example.kabar.model.Articles
import com.example.kabar.remote.DataBaseRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DatabaseNewsRepository @Inject constructor(
    private val dataBaseRemoteDataSource: DataBaseRemoteDataSource
) {

    suspend fun getAllNews() = dataBaseRemoteDataSource.getAllNews()

    suspend fun insertNews(articles: Articles) = dataBaseRemoteDataSource.insertNews(articles)

    suspend fun updateNews(articles: Articles) = dataBaseRemoteDataSource.updateNews(articles)

    suspend fun deleteNews(articles: Articles) = dataBaseRemoteDataSource.deleteNews(articles)


}