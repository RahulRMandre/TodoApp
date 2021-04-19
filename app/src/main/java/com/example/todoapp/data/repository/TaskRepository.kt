package com.example.todoapp.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.todoapp.data.api.UserApiService
import com.example.todoapp.data.dao.AllTaskTitleDao
import com.example.todoapp.data.dao.SubTaskDao
import com.example.todoapp.data.dao.TaskDao
import com.example.todoapp.data.dao.UserDao
import com.example.todoapp.data.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException


class TaskRepository(
    private val taskDao: TaskDao,
    private val userDao: UserDao,
    private val allTaskTitleDao: AllTaskTitleDao,
    private val subTaskDao: SubTaskDao,
    private val userApiService: UserApiService,

    ) {
    var allTaskTitlesList: Flow<List<AllTaskTitles>> = allTaskTitleDao.getAllTaskTitles()


    suspend fun getAllTaskTitles(userId:String) {
        try {
            val titleLists=userApiService.getAllTaskTitles(userId)

            allTaskTitleDao.addAll(titleLists)

        } catch (e: Exception) {
            Log.d("insert all task error", e.toString())
        }


    }

    val user: Flow<List<User>> = userDao.getUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllTaskTitles(allTaskTitles: AllTaskTitles) {
        try {

            userApiService.addTaskTitle(AllTaskTitlesDb(allTaskTitles.taskListTitle,allTaskTitles.userId))
            userApiService.getAllTaskTitles(allTaskTitles.userId).forEach {
                allTaskTitleDao.insert(it)
            }

        } catch (e: Exception) {
            Log.d("insert all task error", e.toString())
        }

    }

    suspend fun loginUser(user: User): Boolean {
        return try {
            val dbUser=userApiService.getUserId(user.email, user.password)
            userDao.loginUser(dbUser)
            true
        } catch (e: Exception) {
            Log.d("error", e.toString())
            false
        }

    }


    suspend fun logoutUser(user: User) {
        userDao.logoutUser(user)
    }

    fun getTask(id: String): MutableLiveData<Task> {
        return taskDao.getTask(id).asLiveData() as MutableLiveData<Task>

    }

    suspend fun  initTasks(id:String){
        try {
            taskDao.addAll(userApiService.getAllTasks(id))
        }
        catch (e:java.lang.Exception){
            Log.d("error",e.toString())
        }

    }
    fun getSubTask(parentId: String): Flow<List<SubTask>> {
        return subTaskDao.getSubTask(parentId)
    }

    suspend fun insertTask(task: Task, subTasks: ArrayList<SubTask>?) {
      /*  taskDao.addTask(task)
        subTasks?.forEach {
            subTaskDao.addSubTask(it)
        }*/
        val taskDb=TaskDb(
            task.taskListId,
            task.title,
            task.details,
            task.completionDate,
            task.isComplete,
            task.createdAt
        )
        try {
            userApiService.addNewTask(taskDb)
            initTasks(task.taskListId)
        }
        catch (e:HttpException){
            Log.d("error", e.toString())
        }


    }


    fun getTasks(taskListId: String): MutableLiveData<ArrayList<Task>> {
        return taskDao.getTasks(taskListId).asLiveData() as MutableLiveData<ArrayList<Task>>
    }

    suspend fun updateTask(task: Task,subTasks: ArrayList<SubTask>?) {
        if (task != null) {
            userApiService.updateTask(task)
            initTasks(task.taskListId)
        }
    }


}