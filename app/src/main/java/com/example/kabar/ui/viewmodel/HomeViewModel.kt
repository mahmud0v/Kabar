package com.example.kabar.ui.viewmodel

import androidx.lifecycle.*
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
    private val databaseRepository: DatabaseNewsRepository,
) : ViewModel() {

    private val trendNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val trendNewsLiveData: LiveData<KabarResult<NewsResponse>> = trendNewsMutableLiveData

    private val searchNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val searchNewsLiveData = searchNewsMutableLiveData

    private val newsDatabaseMutableLiveData = MutableLiveData<List<Articles>>()
    val newsDatabaseLiveData: LiveData<List<Articles>> = newsDatabaseMutableLiveData

    private val checkArticleMutableLiveData = MutableLiveData<Articles>()
    val checkArticleLiveData: LiveData<Articles> = checkArticleMutableLiveData


    fun getTrendNews(category: String) = viewModelScope.launch {
        trendNewsMutableLiveData.value = KabarResult.Loading()
        networkRepository.getTrendNews(category).collect {
            trendNewsMutableLiveData.value = it
        }

    }

    fun getSearchNews(searchWord: String) = viewModelScope.launch {
        searchNewsMutableLiveData.value = KabarResult.Loading()
        networkRepository.getSearchNews(searchWord).collect {
            searchNewsLiveData.value = it
        }
    }

    fun getNewsFromDatabase() = viewModelScope.launch {
        newsDatabaseMutableLiveData.value = databaseRepository.getAllNews()

    }

    fun checkArticleDB(title: String) = viewModelScope.launch {
        checkArticleMutableLiveData.value = databaseRepository.checkArticle(title)
    }


    fun insertNews(articles: Articles) = viewModelScope.launch {
        databaseRepository.insertNews(articles)
    }

    fun deleteNews(title: String) = viewModelScope.launch {
        databaseRepository.deleteNews(title)
    }

    fun updateNews(articles: Articles) = viewModelScope.launch {
        databaseRepository.updateNews(articles)
    }


}