package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.model.Task

class TaskListViewModel:ViewModel() {
    var tasks = MutableLiveData<ArrayList<Task>>()
}