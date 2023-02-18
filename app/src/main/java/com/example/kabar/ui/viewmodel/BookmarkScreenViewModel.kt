package com.example.kabar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabar.model.Articles
import com.example.kabar.repository.DatabaseNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkScreenViewModel @Inject constructor(
    private val databaseNewsRepository: DatabaseNewsRepository
) : ViewModel() {

    private var databaseMutableLiveData = MutableLiveData<List<Articles>>()
    val liveData: LiveData<List<Articles>> = databaseMutableLiveData


    fun getAllArticles() = viewModelScope.launch {
        databaseMutableLiveData.value = databaseNewsRepository.getAllNews()
    }




}