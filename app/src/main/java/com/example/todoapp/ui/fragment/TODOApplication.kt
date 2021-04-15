package com.example.todoapp.ui.fragment

import android.app.Application
import com.example.todoapp.data.database.TaskRoomDatabase
import com.example.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TODOApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy{TaskRoomDatabase.getDatabase(this,applicationScope)}
    val repository by lazy { TaskRepository(database.taskDao(),database.userDao(),database.allTaskTitlesDao()) }

}