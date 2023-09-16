package com.example.projectmanagestaff.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.projectmanagestaff.MyApplication
import com.example.projectmanagestaff.R
import com.example.projectmanagestaff.databinding.FragmentLoginBinding
import com.example.projectmanagestaff.ui.afterTextChanged
import com.example.projectmanagestaff.ui.viewmodel.ProfileViewModel
import com.example.projectmanagestaff.ui.viewmodel.ProfileViewModelFactory


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: ProfileViewModel by activityViewModels {
        val repository = (requireActivity().application as MyApplication).repository
        ProfileViewModelFactory(repository)
    }
    private lateinit var navController: NavController
    private lateinit var saveStateHandle: SavedStateHandle
    private var isButtonLoginClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        setUpAction()
        setUpObserve()
        return binding.root
    }
    private fun setUpAction() {
        binding.btnLogin.setOnClickListener {

        }
        binding.btnCancel.setOnClickListener{
            saveStateHandle[LOGIN_SUCCESSFUL] = false
            navController.popBackStack()
        }
        binding.editLoginEmail.afterTextChanged {
            val email = binding.editLoginEmail.text.toString()
            val password = binding.editLoginPassword.text.toString()
            viewModel.login(email,password)

        }
        binding.editLoginPassword.afterTextChanged {
            val email = binding.editLoginEmail.text.toString()
            val password = binding.editLoginPassword.text.toString()
            viewModel.login(email,password)

        }
    }
    private fun  setUpObserve() {

    }
    companion object {
        const val LOGIN_SUCCESSFUL="LOGIN_SUCCESSFUL"
    }

}