package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentTaskDetailsBinding
import com.example.todoapp.ui.fragment.TODOApplication
import com.example.todoapp.ui.viewmodel.TaskViewModel
import com.example.todoapp.ui.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class TaskDetailsFragment : Fragment() {

    lateinit var binding: FragmentTaskDetailsBinding
    lateinit var parentView: View
    lateinit var viewmodel: TaskViewModel
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString("id").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        parentView = binding.root
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()





        binding.update.setOnClickListener {
            CoroutineScope(IO).launch {
                viewmodel.update(binding.taskViewModel!!.task.value!!,null)
            }
            findNavController().popBackStack()
        }

    }

    fun initView() {
        val app = activity?.applicationContext as TODOApplication
        viewmodel = ViewModelProvider(
            this,
            TaskViewModelFactory(app.repository)
        ).get(TaskViewModel::class.java)
        viewmodel.init(id)
    }


    override fun onResume() {
        super.onResume()

        binding.taskViewModel = viewmodel


        viewmodel.task.observe(viewLifecycleOwner, Observer {
            binding.taskViewModel = viewmodel
        })
        viewmodel.subTask?.observe(viewLifecycleOwner, Observer {
            binding.taskViewModel = viewmodel
        })


    }
}