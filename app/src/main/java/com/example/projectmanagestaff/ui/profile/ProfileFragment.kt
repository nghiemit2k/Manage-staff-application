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
import com.example.projectmanagestaff.data.model.User
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.user.observe(viewLifecycleOwner) {
            if(it==null) {
                val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
                navController.navigate(action)
            } else {
                showProfile(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.currentBackStackEntry!!.savedStateHandle.remove<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
    }
    private fun showProfile(user: User?) {
        user?.let {
            binding.textProfileFullname.text = getString(R.string.txt_fullname,user.fullName)
            binding.textProfileEmail.text = getString(R.string.txt_email,user.email)
            binding.textProfileBirthdate.text = getString(R.string.txt_birthdate,user.birthdate)
            binding.textProfilePhoneNumber.text = getString(R.string.txt_phonenumber,user.phoneNumber)
            binding.textProfileGender.text = getString(R.string.txt_gender,user.gender)
        }
    }

}