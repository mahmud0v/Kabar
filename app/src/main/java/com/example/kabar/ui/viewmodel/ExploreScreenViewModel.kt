package com.example.kabar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabar.model.ExploreTopicEntity
import com.example.kabar.model.NewsResponse
import com.example.kabar.repository.ExploreTopicRepository
import com.example.kabar.repository.NetworkNewsRepository
import com.example.kabar.utils.KabarResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreScreenViewModel @Inject constructor(
    private val exploreTopicRepository: ExploreTopicRepository,
    private val networkNewsRepository: NetworkNewsRepository
) : ViewModel() {
    private var exploreMutableLiveData = MutableLiveData<ExploreTopicEntity?>()
    val exploreLiveData: LiveData<ExploreTopicEntity?> = exploreMutableLiveData

    private var topicsMutableLiveData = MutableLiveData<List<ExploreTopicEntity>?>()
    val topicsLiveData: LiveData<List<ExploreTopicEntity>?> = topicsMutableLiveData

    private var trendNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val trendNewsLiveData: LiveData<KabarResult<NewsResponse>> = trendNewsMutableLiveData


    init {
        getAllTopic()
    }

    fun getAllTopic() = viewModelScope.launch {
        topicsMutableLiveData.value = exploreTopicRepository.getAllTopics()
    }

    fun getTopic(title: String) = viewModelScope.launch {
        exploreMutableLiveData.value = exploreTopicRepository.getTopic(title)
    }

    fun insertTopic(insertTopic: ExploreTopicEntity) = viewModelScope.launch {
        exploreTopicRepository.insertTopic(insertTopic)
    }

    fun updateTopic(updateTopic: ExploreTopicEntity) = viewModelScope.launch {
        exploreTopicRepository.updateTopic(updateTopic)
    }

    fun trendsNews(category: String) = viewModelScope.launch {
        trendNewsMutableLiveData.value = KabarResult.Loading()
        networkNewsRepository.getSearchNews(category).collect {
            trendNewsMutableLiveData.value = it
        }
    }




}