package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.todoapp.data.model.User
import com.example.todoapp.data.repository.TaskRepository

class UserViewModel(val repository: TaskRepository) :ViewModel(){
    var user:LiveData<User> = repository.user.asLiveData()

    suspend fun insert(user: User) {
        repository.loginUser(user)
    }
}

class UserViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  UserViewModel(repository) as T
    }

}