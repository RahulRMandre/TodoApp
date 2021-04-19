package com.example.todoapp.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.todoapp.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = REPLACE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updatesTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

/*    @Query("select * from Task where Task.parentId=:parentId")
    suspend fun getTasks(parentId:String):List<Task>*/

    @Query("select * from Task where Task.taskListId=:taskListId")
     fun getTasks(taskListId:String):Flow<List<Task>>

    @Query("select * from Task where Task.id=:taskId")
     fun getTask(taskId:String):Flow<Task>

    @Query("SELECT * from Task")
    suspend fun  getAllTasks():List<Task>

    @Insert(onConflict = REPLACE)
    suspend fun addAll(allTasks: List<Task>)


}