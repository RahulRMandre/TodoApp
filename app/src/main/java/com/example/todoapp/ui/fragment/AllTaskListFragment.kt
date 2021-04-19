package com.example.todoapp.ui.fragment

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
import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.model.TitleType
import com.example.todoapp.databinding.FragmentAllTaskListBinding
import com.example.todoapp.ui.adaptor.AllTaskListAdapter
import com.example.todoapp.ui.viewmodel.AllTaskListViewModel
import com.example.todoapp.ui.viewmodel.AllTaskListViewModelFactory
import com.example.todoapp.ui.viewmodel.UserViewModel
import com.example.todoapp.ui.viewmodel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class AllTaskListFragment : Fragment() {

    lateinit var binding: FragmentAllTaskListBinding
    private lateinit var defaultTaskListViewModel: AllTaskListViewModel
    private lateinit var userViewModel: UserViewModel
     var bundle= Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllTaskListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUser()
        initDefaultTask()
        binding.newList.setOnClickListener {

            findNavController().navigate(R.id.action_allTaskListFragment_to_AllTaskListBottomSheet,bundle)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private  fun initUser() {
        val app = activity?.applicationContext as TODOApplication
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(app.repository)
        ).get(UserViewModel::class.java)

        userViewModel.user.observe(viewLifecycleOwner, Observer {
            binding.userViewModel=userViewModel
            if(userViewModel.user.value!=null){

                bundle.putString("userId", userViewModel.user.value!![0].id)
                CoroutineScope(IO).launch {
                    defaultTaskListViewModel.init(userViewModel.user.value!![0].id!!)

                }
            }
        })

    }




    private fun initDefaultTask() {

        val app = activity?.applicationContext as TODOApplication
        defaultTaskListViewModel =
            ViewModelProvider(this, AllTaskListViewModelFactory(app.repository)).get(
                AllTaskListViewModel::class.java
            )


        val defaultAdapter = AllTaskListAdapter(defaultTaskListViewModel)
        defaultTaskListViewModel.titles.observe(viewLifecycleOwner, Observer {
            defaultAdapter.notifyDataSetChanged()
        })
        binding.defaultTaskList.layoutManager = LinearLayoutManager(context)
        binding.defaultTaskList.adapter = defaultAdapter;


    }


}