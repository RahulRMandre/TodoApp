package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.api.RetrofitBuilder
import com.example.todoapp.data.api.UserApiService
import com.example.todoapp.data.model.User
import com.example.todoapp.databinding.FragmentLoginBinding
import com.example.todoapp.ui.viewmodel.UserViewModel
import com.example.todoapp.ui.viewmodel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    lateinit var binding:FragmentLoginBinding
    lateinit var userViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUser()
    }

    override fun onResume() {
        super.onResume()

        binding.loginFragment.setOnClickListener {
            if(userViewModel.user.value==null || userViewModel.user.value!!.isEmpty()) {
            CoroutineScope(IO).launch {
                    userViewModel.insert(User("rahul", "rahul@email.com", "abc",""))
                }

            }

        }
    }

    private  fun initUser() {
        val app = activity?.applicationContext as TODOApplication
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(app.repository)
        ).get(UserViewModel::class.java)

        userViewModel.user.observe(viewLifecycleOwner, Observer {
            if(userViewModel.user.value!=null && userViewModel.user.value!!.isNotEmpty()){
                findNavController().navigate(R.id.action_loginFragment_to_allTaskListFragment)
            }
        })

    }
}