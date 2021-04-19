package com.example.todoapp.ui.adaptor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.model.Task
import com.example.todoapp.databinding.AllTaskListViewBinding
import com.example.todoapp.databinding.TaskListViewBinding
import com.example.todoapp.ui.fragment.AllTaskListFragment
import com.example.todoapp.ui.viewmodel.AllTaskListViewModel
import com.example.todoapp.ui.viewmodel.TaskListViewModel
import com.example.todoapp.ui.viewmodel.TaskViewModel


class AllTaskListAdapter(private val dataSet: AllTaskListViewModel) :
    RecyclerView.Adapter<AllTaskListAdapter.ViewHolder>() {

    class ViewHolder(val binding: AllTaskListViewBinding) : RecyclerView.ViewHolder(binding.root){}



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = AllTaskListViewBinding.inflate(layoutInflater,viewGroup,false)
        return ViewHolder(binding)

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentTitle:AllTaskTitles?= dataSet.titles.value?.get(position)
        if (currentTitle != null) {
            viewHolder.binding.alltasktitle=currentTitle
        }
        viewHolder.binding.title.setOnClickListener {
            val bundle= Bundle()
            bundle.putString("parentId",currentTitle?.id)
            it.findNavController().navigate(R.id.action_allTaskListFragment_to_taskListFragment,bundle)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.titles.value?.size?:0

}

