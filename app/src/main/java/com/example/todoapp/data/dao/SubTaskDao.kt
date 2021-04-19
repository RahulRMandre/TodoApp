package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTaskDao {

    @Query("select * from SubTask where SubTask.parentId=:taskId")
    fun getSubTask(taskId:String): Flow<List<SubTask>>

    @Insert
    suspend fun addSubTask(subTask: SubTask)

}