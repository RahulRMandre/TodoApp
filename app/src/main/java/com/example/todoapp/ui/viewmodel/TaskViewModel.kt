package com.example.todoapp.ui.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.model.SubTaskDb
import com.example.todoapp.data.model.Task
import com.example.todoapp.data.repository.TaskRepository

class TaskViewModel(val repository: TaskRepository) : ViewModel() {
    var task = MutableLiveData<Task>()
    var subTask = MutableLiveData<ArrayList<SubTask>>()

    fun init(id: String) {
        task = repository.getTask(id) as MutableLiveData<Task>
        subTask.value=ArrayList()



    }

    suspend fun insert(newTask: Task, newSubTask: ArrayList<SubTask>?) {
        repository.insertTask(newTask, newSubTask)
    }

    suspend fun update(task: Task, subTask: ArrayList<SubTask>?) {
        repository.updateTask(task, subTask)
    }

    suspend fun addSubTask(subTaskDb: SubTaskDb) {
        repository.insertSubTask(subTaskDb)
        repository.initSubTask(subTaskDb.taskId)
        initSubtask(subTaskDb.taskId)
    }

    suspend fun deleteSubTask(id: String, taskId:String) {
        repository.deleteSubTask(id)
        repository.initSubTask(taskId)
        initSubtask(taskId)
    }

     suspend fun initSubtask(id: String){
         val res=repository.getSubTasks(id)
         subTask.value?.clear()
         subTask.value?.addAll(res)
     }

    suspend fun initSubTaskNetwork(id: String) {
        repository.initSubTask(id)


    }

    suspend fun updateSubTask(subTask: SubTask) {
        repository.updateSubTask(subTask)
        repository.initSubTask(subTask.taskId)
        initSubtask(subTask.taskId)
    }


}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repository) as T
    }
}