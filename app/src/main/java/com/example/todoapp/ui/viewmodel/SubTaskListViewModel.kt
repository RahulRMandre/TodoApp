package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class SubTaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    fun deleteSubTask(id: String) {
        viewModelScope.launch {
            repository.deleteSubTask(id);
        }

    }

    var subTasks = MutableLiveData<ArrayList<SubTask>>()


}

class SubTaskListViewModelFactory(private val repository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubTaskListViewModel(repository) as T
    }
}