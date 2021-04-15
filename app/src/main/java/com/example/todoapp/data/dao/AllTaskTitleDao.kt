package com.example.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.model.AllTaskTitles
import kotlinx.coroutines.flow.Flow
@Dao
interface AllTaskTitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allTaskTitles: AllTaskTitles)

    @Delete
    suspend fun deleteTaskTitle(allTaskTitles: AllTaskTitles)

    @Query("SELECT * from AllTaskTitles")
     fun getAllTaskTitles(): Flow<List<AllTaskTitles>>
}