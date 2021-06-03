package com.example.melichallenge.ui.searchItem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    val itemToSearch = MutableLiveData<String>()

    init {
        itemToSearch.value = ""
    }

    fun isValidItem(): Boolean {
        return !itemToSearch.value.isNullOrEmpty()
    }
}