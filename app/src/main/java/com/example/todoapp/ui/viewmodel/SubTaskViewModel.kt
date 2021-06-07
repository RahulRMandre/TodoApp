package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.repository.TaskRepository

class SubTaskViewModel( val repository: TaskRepository) : ViewModel() {
    var subTask = MutableLiveData<SubTask>()

}


class SubTaskViewModelFactory(private val repository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubTaskViewModel(repository) as T
    }
}