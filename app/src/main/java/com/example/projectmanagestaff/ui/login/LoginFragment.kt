package com.example.projectmanagestaff.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import com.google.android.material.snackbar.Snackbar


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveStateHandle = navController.previousBackStackEntry!!.savedStateHandle
        saveStateHandle[LOGIN_SUCCESSFUL]= false
    }
    private fun setUpAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.editLoginEmail.text.toString()
            val password = binding.editLoginPassword.text.toString()
            viewModel.login(email,password)
            isButtonLoginClicked = true
        }
        binding.btnCancel.setOnClickListener{
            saveStateHandle[LOGIN_SUCCESSFUL] = false
            navController.popBackStack()
        }
        binding.editLoginEmail.afterTextChanged {
            val email = binding.editLoginEmail.text.toString()
            val password = binding.editLoginPassword.text.toString()
            viewModel.loginDataChanged(email,password)

        }
        binding.editLoginPassword.afterTextChanged {
            val email = binding.editLoginEmail.text.toString()
            val password = binding.editLoginPassword.text.toString()
            viewModel.loginDataChanged(email,password)

        }
    }
    private fun  setUpObserve() {
        viewModel.user.observe(viewLifecycleOwner) {
            if(isButtonLoginClicked) {
                isButtonLoginClicked = false
                if(it==null) {
                    closeKeyBoard()
                    showError()
                } else {
                    saveStateHandle[LOGIN_SUCCESSFUL]=true
                    navController.popBackStack()
                }
            }
        }
        viewModel.loginFormState.observe(viewLifecycleOwner) {
            if(it.emailError != null) {
                binding.editLoginEmail.error = getString(it.emailError)
                binding.btnLogin.isEnabled = false
            } else if(it.passwordError != null) {
                binding.editLoginPassword.error = getString(it.passwordError)
                binding.btnLogin.isEnabled = false
            } else if(it.isCorrect) {
                binding.btnLogin.isEnabled=  true
            }
        }
    }

    private fun showError() {
        val snackbar = Snackbar.make(
            binding.root,R.string.error_login_failed,Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }


    private fun closeKeyBoard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken,0)
    }

    companion object {
        const val LOGIN_SUCCESSFUL="LOGIN_SUCCESSFUL"
    }

}