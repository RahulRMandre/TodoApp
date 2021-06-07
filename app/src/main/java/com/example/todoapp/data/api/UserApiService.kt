package com.example.todoapp.data.api

import com.example.todoapp.data.model.*
import retrofit2.http.*


interface UserApiService {
    @GET("users")
    suspend fun getUserId(@Query("email") email: String, @Query("password") password: String): User

    @POST("users")

    suspend fun addUser(@Body user: UserDb)


    @POST("taskListTitle")
   suspend fun addTaskTitle(@Body addTaskTitles: AllTaskTitlesDb)



    @GET("taskListTitle")
    suspend fun getAllTaskTitles(@Query("userId")userId: String): List<AllTaskTitles>


    @POST("tasks")
    suspend fun addNewTask(@Body task: TaskDb)

    @GET("tasks")
    suspend fun getAllTasks(@Query("taskListId")taskListId:String):List<Task>

    @GET("task")
    suspend fun getTask(@Query("taskId")taskId:String):Task

    @POST("tasks")
    suspend fun updateTask(@Body task: Task)

    @POST("subTasks")
    suspend fun addSubTask(@Body subTaskDb: SubTaskDb)

    @POST("subTasks")
    suspend fun updateSubTask(@Body subTask: SubTask)


    @GET("subTasks")
    suspend fun getAllSubTasks(@Query("taskId")taskId:String):List<SubTask>

    @DELETE("subTasks")
    suspend fun deleteSubTask(@Query("id")id:String)


}