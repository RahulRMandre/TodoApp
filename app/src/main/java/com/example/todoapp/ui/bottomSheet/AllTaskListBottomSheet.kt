package com.example.todoapp.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.model.TitleType
import com.example.todoapp.databinding.BottomSheetAllTaskListBinding
import com.example.todoapp.ui.fragment.TODOApplication
import com.example.todoapp.ui.viewmodel.AllTaskTitlesViewModel
import com.example.todoapp.ui.viewmodel.UserViewModel
import com.example.todoapp.ui.viewmodel.UserViewModelFactory
import com.example.todoapp.ui.viewmodel.AllTaskTitlesViewModelFactory

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AllTaskListBottomSheet:BottomSheetDialogFragment() {
lateinit var binding:BottomSheetAllTaskListBinding
lateinit var allTaskTitlesViewModel: AllTaskTitlesViewModel
lateinit var userId:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetAllTaskListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getString("userId").toString()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.submit.setOnClickListener {
            if(binding.listName.text.isNotBlank()) {
                CoroutineScope(IO).launch {
                    allTaskTitlesViewModel.insert(
                        AllTaskTitles(
                            binding.listName.text.toString(),
                            userId,
                            ""
                        )
                    )
                }
                findNavController().popBackStack()

            }
        }
    }

    private fun initViewModel(){
        val app = activity?.applicationContext as TODOApplication
        allTaskTitlesViewModel = ViewModelProvider(
            this,
            AllTaskTitlesViewModelFactory(app.repository)
        ).get(AllTaskTitlesViewModel::class.java)
    }

}