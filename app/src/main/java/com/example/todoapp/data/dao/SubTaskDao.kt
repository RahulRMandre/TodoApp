package com.example.todoapp.data.dao

import androidx.room.*
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTaskDao {



    @Query("select * from SubTask where SubTask.taskId=:taskId")
    fun getSubTask(taskId:String):Flow<List<SubTask>>

    @Query("SELECT * FROM SubTask")
    suspend fun getAllSubTasks():List<SubTask>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubTask(subTask: SubTask)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubTasks(subTasks: List<SubTask>)

    @Query("DELETE FROM SubTask WHERE SubTask.id=:id")
    suspend fun deleteSubtask(id: String)

}