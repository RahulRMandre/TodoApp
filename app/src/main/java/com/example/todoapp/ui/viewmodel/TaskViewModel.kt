package com.example.todoapp.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.model.Task
import com.example.todoapp.data.model.TimeStatus
import com.example.todoapp.data.repository.TaskRepository

class TaskViewModel : ViewModel() {
    lateinit var task: MutableLiveData<Task>
    lateinit var subTask: MutableLiveData<SubTask>



}