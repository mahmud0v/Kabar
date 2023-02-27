package com.example.kabar.api

import com.example.kabar.model.NewsResponse
import com.example.kabar.utils.Constants.Companion.API_KEY1
import com.example.kabar.utils.Constants.Companion.API_KEY2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun getTrendNews(
        @Query("country") country: String = "us",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = API_KEY2
    ): Response<NewsResponse>


    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q") q: String,
        @Query("sortBy")sortBy:String = "popularity",
        @Query("language")language:String = "en",
        @Query("apiKey") apiKey: String= API_KEY2
    ):Response<NewsResponse>




}