package com.example.kabar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabar.model.Articles
import com.example.kabar.model.NewsResponse
import com.example.kabar.repository.DatabaseNewsRepository
import com.example.kabar.repository.NetworkNewsRepository
import com.example.kabar.utils.KabarResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkNewsRepository,
    private val databaseRepository: DatabaseNewsRepository
) : ViewModel() {

    private val trendNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val trendNewsLiveData: LiveData<KabarResult<NewsResponse>> = trendNewsMutableLiveData

    private val latestNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val latestNewsLiveData = latestNewsMutableLiveData

    private val newsDatabaseMutableLiveData = MutableLiveData<List<Articles>>()
    val newsDatabaseLiveData: LiveData<List<Articles>> = newsDatabaseMutableLiveData

    fun getTrendNews(category: String) = viewModelScope.launch {
        trendNewsMutableLiveData.value = KabarResult.Loading()
        networkRepository.getTrendNews(category).collect {
            trendNewsMutableLiveData.value = it
        }

    }

    fun getLatestNews(category: String) = viewModelScope.launch {
        networkRepository.getLatestNews(category).collect {
            latestNewsMutableLiveData.value = it
        }
    }

    fun getNewsFromDatabase() = viewModelScope.launch {
        newsDatabaseMutableLiveData.value = databaseRepository.getAllNews()

    }

    fun insertNews(articles: Articles) = viewModelScope.launch {
         databaseRepository.insertNews(articles)
    }

    fun deleteNews(id:Int) = viewModelScope.launch {
        databaseRepository.deleteNews(id)
    }

}