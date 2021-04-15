package com.example.todoapp.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.data.dao.AllTaskTitleDao
import com.example.todoapp.data.dao.TaskDao
import com.example.todoapp.data.dao.UserDao
import com.example.todoapp.data.model.*
import kotlinx.coroutines.flow.Flow


class TaskRepository(
    private val taskDao: TaskDao,
    private val userDao: UserDao,
    private val allTaskTitleDao: AllTaskTitleDao
) {
    val allTaskTitlesList: Flow<List<AllTaskTitles>> = allTaskTitleDao.getAllTaskTitles()
    val user:Flow<User> = userDao.getUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllTaskTitles(allTaskTitles: AllTaskTitles) {
        allTaskTitleDao.insert(allTaskTitles)
    }

    suspend fun loginUser(user: User){
        userDao.loginUser(user)
    }
    suspend fun logoutUser(user: User){
        userDao.logoutUser(user)
    }



}