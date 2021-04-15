package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class AllTaskListViewModel(private val repository: TaskRepository) :ViewModel(){
     var titles=MutableLiveData<ArrayList<AllTaskTitles>>()
     init {
         titles=repository.allTaskTitlesList.asLiveData() as MutableLiveData<ArrayList<AllTaskTitles>>
     }


     fun insert(allTaskTitles: AllTaskTitles){
          viewModelScope.launch {
                    repository.insertAllTaskTitles(allTaskTitles)

          }
     }


}