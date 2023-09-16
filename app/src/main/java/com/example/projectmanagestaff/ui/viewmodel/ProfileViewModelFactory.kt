package com.example.projectmanagestaff.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectmanagestaff.data.repository.ProfileRepository

class ProfileViewModelFactory(private val repository: ProfileRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unkonwn ViewModel class")
    }

}