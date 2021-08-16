package com.example.myapplication.myapplication.newss.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.myapplication.newss.repository.Repository

class NewsViewModelProviderFactory(
    private val repository: Repository
) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}