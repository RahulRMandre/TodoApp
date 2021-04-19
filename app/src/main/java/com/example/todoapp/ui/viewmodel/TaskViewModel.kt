package com.example.todoapp.ui.viewmodel


import androidx.lifecycle.*
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.model.Task
import com.example.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(val repository: TaskRepository) : ViewModel() {
    var task= MutableLiveData<Task>()
    var subTask: MutableLiveData<ArrayList<SubTask>>? = null

     fun init(id: String) {

         task = repository.getTask(id) as MutableLiveData<Task>

        if (task != null && task!!.value != null) {
            subTask = repository.getSubTask(task?.value!!.id)
                .asLiveData() as MutableLiveData<ArrayList<SubTask>>
        }
    }

    suspend fun insert(newTask: Task, newSubTask: ArrayList<SubTask>?) {
        repository.insertTask(newTask, newSubTask)
    }

    suspend fun update(task: Task,subTask: ArrayList<SubTask>?) {
        repository.updateTask(task,subTask)
    }



}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repository) as T
    }
}