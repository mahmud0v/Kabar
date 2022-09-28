package com.example.kabar.repository

import com.example.kabar.model.NewsResponse
import com.example.kabar.remote.RemoteDataSource
import com.example.kabar.utils.KabarResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {


    suspend fun getTrendNews(category:String): Flow<KabarResult<NewsResponse>> {
        val trendNews = remoteDataSource.getTrendNews(category)
        return if (trendNews.isSuccessful){
            flow {
                emit(KabarResult.Success(trendNews.body()!!))
            }
        } else{
            flow {
                emit(KabarResult.Error(trendNews.message()))
            }
        }

    }

    suspend fun getLatestNews(category: String): Flow<KabarResult<NewsResponse>> {
        val latestNews = remoteDataSource.getLatestNews(category)
        return if (latestNews.isSuccessful) {
            flow {
                emit(KabarResult.Success(latestNews.body()!!))
            }
        } else {
            flow<KabarResult<NewsResponse>> {
                emit(KabarResult.Error(latestNews.message()))
            }
        }
    }
}