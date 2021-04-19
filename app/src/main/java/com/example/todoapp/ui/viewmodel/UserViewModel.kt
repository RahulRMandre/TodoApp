package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.todoapp.data.model.User
import com.example.todoapp.data.repository.TaskRepository

class UserViewModel(val repository: TaskRepository) :ViewModel(){
     var user= MutableLiveData<List<User>>()
    init {
        user=repository.user.asLiveData() as MutableLiveData<List<User>>
    }
    suspend fun insert(user: User):Boolean {
        return repository.loginUser(user)
    }
}

class UserViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  UserViewModel(repository) as T
    }

}