package com.example.todoapp.ui .fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.model.Task
import com.example.todoapp.databinding.FragmentTaskDetailsBinding
import com.example.todoapp.ui.viewmodel.TaskViewModel


class TaskDetailsFragment : Fragment() {

    lateinit var binding:FragmentTaskDetailsBinding
    lateinit var parentView:View
    lateinit var viewmodel:TaskViewModel
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentTaskDetailsBinding.inflate(inflater,container,false)
        parentView= binding.root
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewmodel = ViewModelProvider(this).get(TaskViewModel::class.java)


        var nameObserver = Observer<Task> { it ->
            // Update the UI, in this case, a TextView.
            binding.title.setText(it.title)
        }
        viewmodel.task.observe(viewLifecycleOwner, nameObserver)




        //binding.taskViewModel=viewmodel
        //viewmodel.update()
        //viewmodel.task.value?.title  ="op"
    }


}