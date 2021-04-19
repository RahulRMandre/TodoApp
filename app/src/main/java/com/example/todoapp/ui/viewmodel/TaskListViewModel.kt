package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.data.model.Task
import com.example.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(private val repository: TaskRepository):ViewModel() {
    var tasks = MutableLiveData<ArrayList<Task>>()
init {
    viewModelScope.launch {
      //  tasks=repository.allTaskList as MutableLiveData<ArrayList<Task>>
    }

}

     fun init(title: String) {
            tasks=repository.getTasks(title)
    }

   suspend fun insert(task: Task, subTask: List<SubTask>?, parentId: String) {
        repository.insertTask(task,null)
    }

    suspend fun initNetwork(title: String) {
        repository.initTasks(title)
    }
}

class TaskListViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  TaskListViewModel(repository) as T
    }

}