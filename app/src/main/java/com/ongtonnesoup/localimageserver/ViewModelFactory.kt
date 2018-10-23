package com.ongtonnesoup.localimageserver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val repository: PageRepository,
    private val mapper: UiPageMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) = PageViewModel(repository, mapper) as T

}