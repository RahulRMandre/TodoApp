package com.example.todoapp.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.model.Task
import com.example.todoapp.data.model.TimeStatus
import com.example.todoapp.data.model.TitleType
import com.example.todoapp.databinding.BottomSheetAllTaskListBinding
import com.example.todoapp.databinding.BottomSheetTaskListBinding
import com.example.todoapp.ui.fragment.TODOApplication
import com.example.todoapp.ui.fragment.TaskListFragment
import com.example.todoapp.ui.viewmodel.*

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class TaskListBottomSheet: BottomSheetDialogFragment()  {
    lateinit var binding:BottomSheetTaskListBinding
    lateinit var taskViewModel:TaskViewModel
    lateinit var id:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetTaskListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id= arguments?.getString("parentId").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        binding.save.setOnClickListener {
            if(binding.taskTitle.text.isNotBlank()) {
                CoroutineScope(IO).launch {
                    taskViewModel.insert(
                        Task(
                            id,
                            Date().time.toString(),
                             binding.taskTitle.text.toString(),
                            "",
                            0,
                            0,
                            0


                            //timeStatus = TimeStatus.INVALID
                        ),null
                    )
                }


            }

            findNavController().popBackStack()

        }
    }

    private fun initViewModel(){
        val app = activity?.applicationContext as TODOApplication
        taskViewModel = ViewModelProvider(
            viewModelStore,
            TaskViewModelFactory(app.repository)
        ).get(TaskViewModel::class.java)
    }

}