package com.example.kabar.remote

import com.example.kabar.api.NewsApi
import com.example.kabar.utils.SelectableTopicsData
import javax.inject.Inject

class NetworkRemoteDataSource @Inject constructor(private val newsApi: NewsApi) {


    suspend fun getTrendNews(category: String) = newsApi.getTrendNews(category = category)

    suspend fun getLatestNews(category: String) = newsApi.getLatestNews(q = category)


}