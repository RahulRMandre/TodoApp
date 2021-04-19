package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTaskListBinding
import com.example.todoapp.ui.adaptor.AllTaskListAdapter
import com.example.todoapp.ui.adaptor.TaskListAdapter
import com.example.todoapp.ui.viewmodel.TaskListViewModel
import com.example.todoapp.ui.viewmodel.TaskListViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TaskListFragment : Fragment() {

    lateinit var binding: FragmentTaskListBinding
    lateinit var title: String
    lateinit var taskListViewModel: TaskListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString("parentId").toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        binding.addTask.setOnClickListener() {
            val bundle = Bundle()
            bundle.putString("parentId", title)
            it.findNavController()
                .navigate(R.id.action_taskListFragment_to_TaskListBottomSheet, bundle)

        }
    }

    override fun onResume() {
        super.onResume()

    }


    private fun initView() {

        val app = activity?.applicationContext as TODOApplication
        taskListViewModel = ViewModelProvider(
            viewModelStore,
            TaskListViewModelFactory(app.repository)
        ).get(TaskListViewModel::class.java)

        CoroutineScope(IO).launch {
            taskListViewModel.initNetwork(title)
        }

        taskListViewModel.init(title)


        val defaultAdapter = TaskListAdapter(taskListViewModel)
        taskListViewModel.tasks.observe(viewLifecycleOwner, Observer {
            defaultAdapter.notifyDataSetChanged()
        })
        binding.taskList.layoutManager = LinearLayoutManager(context)
        binding.taskList.adapter = defaultAdapter;


    }



}