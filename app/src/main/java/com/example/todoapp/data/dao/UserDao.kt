package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.data.model.User
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDao {
    @Insert
    suspend fun loginUser(user: User)

    @Query("Select * from User")
    fun getUser():Flow<List<User>>

    @Delete
    suspend fun logoutUser(user:User)
}