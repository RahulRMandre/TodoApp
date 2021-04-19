
package com.example.todoapp.ui.adaptor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.model.Task
import com.example.todoapp.databinding.TaskListViewBinding
import com.example.todoapp.ui.viewmodel.TaskListViewModel

class TaskListAdapter(private val dataSet: TaskListViewModel) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(val binding: TaskListViewBinding) : RecyclerView.ViewHolder(binding.root)



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = TaskListViewBinding.inflate(layoutInflater,viewGroup,false)
        return ViewHolder(binding)

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentTask: Task?= dataSet.tasks.value?.get(position)
        if (currentTask != null) {
            viewHolder.binding.task=currentTask
        }
        viewHolder.binding.body.setOnClickListener {
            val bundle= Bundle()
            if (currentTask != null) {
                bundle.putString("id",currentTask.id)
            }
            it.findNavController().navigate(R.id.action_taskListFragment_to_taskDetailsFragment,bundle)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.tasks.value?.size?:0

}


