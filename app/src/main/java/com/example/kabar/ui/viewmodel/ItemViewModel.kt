package com.example.kabar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor() : ViewModel() {
    private val mutableItemLiveData = MutableLiveData<Boolean>()
    val itemLivedata: LiveData<Boolean> = mutableItemLiveData


    fun itemClick(){
        mutableItemLiveData.value = true
    }

    fun returnClicked(){
        mutableItemLiveData.value = false
    }

}