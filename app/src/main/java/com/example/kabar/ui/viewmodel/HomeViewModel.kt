package com.example.kabar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabar.model.NewsResponse
import com.example.kabar.repository.NetworkNewsRepository
import com.example.kabar.utils.KabarResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NetworkNewsRepository
) : ViewModel() {

    private val trendNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val trendNewsLiveData: LiveData<KabarResult<NewsResponse>> = trendNewsMutableLiveData

    private val latestNewsMutableLiveData = MutableLiveData<KabarResult<NewsResponse>>()
    val latestNewsLiveData = latestNewsMutableLiveData


    fun getTrendNews(category:String) = viewModelScope.launch {
        trendNewsMutableLiveData.value = KabarResult.Loading()
        repository.getTrendNews(category).collect{
            trendNewsMutableLiveData.value = it
        }

    }

    fun getLatestNews(category: String) = viewModelScope.launch {
        repository.getLatestNews(category).collect {
         latestNewsMutableLiveData.value = it
        }
    }


}