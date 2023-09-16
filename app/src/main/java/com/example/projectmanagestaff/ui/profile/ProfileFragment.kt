package com.example.projectmanagestaff.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.projectmanagestaff.MyApplication
import com.example.projectmanagestaff.R
import com.example.projectmanagestaff.databinding.FragmentProfileBinding
import com.example.projectmanagestaff.ui.home.HomeFragmentDirections
import com.example.projectmanagestaff.ui.login.LoginFragment
import com.example.projectmanagestaff.ui.viewmodel.ProfileViewModel
import com.example.projectmanagestaff.ui.viewmodel.ProfileViewModelFactory


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by activityViewModels {
        val repository = (requireActivity().application as MyApplication).repository
        ProfileViewModelFactory(repository)
    }

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)?.observe(currentBackStackEntry) {success ->
            if(!success) {
                val action = HomeFragmentDirections.actionGlobalHomeFragment2()
                navController.navigate(action)
            }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }


}