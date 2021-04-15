package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.repository.TaskRepository

class AllTaskTitlesViewModel(private val repository: TaskRepository) : ViewModel() {
    suspend fun insert(allTaskTitles: AllTaskTitles) {
    repository.insertAllTaskTitles(allTaskTitles)
    }

    lateinit var allTaskTitles: LiveData<AllTaskTitles>
}

class allTaskTitlesViewModelFactory(private val repository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllTaskTitlesViewModel(repository) as T
    }
}