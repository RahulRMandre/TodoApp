package com.example.todoapp.ui.fragment


import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.model.SubTaskDb
import com.example.todoapp.databinding.FragmentTaskDetailsBinding
import com.example.todoapp.ui.adaptor.SubTaskListAdapter
import com.example.todoapp.ui.viewmodel.TaskViewModel
import com.example.todoapp.ui.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class TaskDetailsFragment : Fragment() {

    lateinit var adapter: SubTaskListAdapter
    lateinit var binding: FragmentTaskDetailsBinding
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
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        binding.update.setOnClickListener {
            CoroutineScope(IO).launch {
                viewmodel.update(binding.taskViewModel!!.task.value!!, null)
                CoroutineScope(Main).launch {
                    findNavController().popBackStack()
                }
            }

        }

        taskCompletedListener()
        addSubtaskListener()
        addSubtaskButtonListener()

    }

    /**
     *alternates task Completed selection
     **/
    private fun taskCompletedListener() {
        binding.taskCompleted.setOnClickListener {
            if (viewmodel.task.value?.finish == 0) {
                viewmodel.task.value?.finish = 1
                taskDone(1)
            } else {
                viewmodel.task.value?.finish = 0
                taskDone(0)
            }
        }
    }

    private fun addSubtaskListener() {
        binding.addSubtask.setOnClickListener {
            showAddSubTask(false)
        }
    }

    private fun addSubtaskButtonListener() {
        binding.btnAdd.setOnClickListener {

            CoroutineScope(IO).launch {
                viewmodel.addSubTask(SubTaskDb(id, binding.subtaskTitle.text.toString(), 0))
                CoroutineScope(Main).launch {
                    showAddSubTask(true)
                    binding.subtaskTitle.text.clear()
                }
            }

        }
    }

    private fun showAddSubTask(show: Boolean) {
        if (!show) {
            binding.addSubtask.visibility = View.INVISIBLE
            binding.subtaskCompleted.visibility = View.VISIBLE
            binding.btnAdd.visibility = View.VISIBLE
            binding.subtaskTitle.visibility = View.VISIBLE
        } else {
            binding.addSubtask.visibility = View.VISIBLE
            binding.subtaskCompleted.visibility = View.INVISIBLE
            binding.btnAdd.visibility = View.INVISIBLE
            binding.subtaskTitle.visibility = View.INVISIBLE
        }
    }



    private fun initView() {
        val app = activity?.applicationContext as TODOApplication
        viewmodel = ViewModelProvider(
            this,
            TaskViewModelFactory(app.repository)
        ).get(TaskViewModel::class.java)
        viewmodel.init(id)

        binding.taskViewModel = viewmodel

        adapter = SubTaskListAdapter(viewmodel)


        viewmodel.task.observe(viewLifecycleOwner, Observer {
            binding.taskViewModel = viewmodel
            viewmodel.task.value?.finish?.let { it -> taskDone(it) }

            CoroutineScope(IO).launch {
                viewmodel.task.value?.let { it -> viewmodel.initSubTaskNetwork(it.id) }
                viewmodel.task.value?.let { it -> viewmodel.initSubtask(it.id) }
                notify()
            }
        })


        viewmodel.subTask.observe(viewLifecycleOwner, Observer {
            binding.taskViewModel = viewmodel
            adapter.notifyDataSetChanged()
        })

        binding.subtaskList.layoutManager = LinearLayoutManager(context)
        binding.subtaskList.adapter = adapter

    }

    private suspend fun notify() {
        CoroutineScope(Main).launch {
            adapter.notifyDataSetChanged()
        }
    }

    private fun taskFavourate(favourate: Int) {
        when (favourate) {
            //     1->binding.taskFavourate.setImageResource(R.drawable.ic_favourate)
            //     0->binding.taskFavourate.setImageResource(R.drawable.ic_not_favourate)

        }
    }

    /**
     *handles task Completed selection
     **/
    private fun taskDone(done: Int) {
        when (done) {
            1 -> {

                binding.taskCompleted.setImageResource(R.drawable.ic_circle_filled)
                binding.title.paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG or binding.title.paintFlags)
            }
            0 -> {

                binding.taskCompleted.setImageResource(R.drawable.ic_circle)
                binding.title.paintFlags =
                    (Paint.STRIKE_THRU_TEXT_FLAG.inv() and binding.title.paintFlags)
            }
        }
    }


}
