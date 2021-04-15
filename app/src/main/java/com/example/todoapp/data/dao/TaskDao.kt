package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.todoapp.data.model.Task
@Dao
interface TaskDao {
    @Insert
    fun addTask(task: Task)

    @Update
    fun updatesTask(task: Task)

    @Delete
    fun deleteTask(task: Task)



}