package com.example.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.databinding.FragmentAllTaskListBinding
import com.example.todoapp.databinding.FragmentTaskListBinding
import com.example.todoapp.ui.adaptor.AllTaskListAdapter
import com.example.todoapp.ui.adaptor.TaskListAdapter
import com.example.todoapp.ui.viewmodel.AllTaskListViewModel
import com.example.todoapp.ui.viewmodel.TaskListViewModel

class TaskListFragment : Fragment() {

    lateinit var binding: FragmentTaskListBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTaskListBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val taskRepo= TaskRepository()
        //initDefaultTask(taskRepo)
       // findNavController().navigate(R.id.action_taskListFragment_to_taskDetailsFragment)

    }




}