package com.example.projectmanagestaff.ui.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectmanagestaff.R
import com.example.projectmanagestaff.data.model.User
import com.example.projectmanagestaff.data.repository.ProfileRepository
import com.example.projectmanagestaff.data.repository.ProfileRepositoryImpl
import com.example.projectmanagestaff.ui.LoginFormState

class ProfileViewModel(private val repositoryImpl: ProfileRepository):ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> =_user
    private val _loginFormState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginFormState

    init {
        _user.value = null
    }
    fun login(email: String, password: String) {

    }
    fun loginDataChanged(email: String,password: String) {
        if(email.isBlank()) {
            _loginFormState.value = LoginFormState(emailError = R.string.error_email_blank)
        } else if(!isUserNameValid(email)) {
            _loginFormState.value = LoginFormState(emailError = R.string.error_email_invalid)
        } else if(!isPasswordValid(password)) {
            _loginFormState.value= LoginFormState(passwordError = R.string.error_password)
        } else {
            _loginFormState.value = LoginFormState(isCorrect = true)
        }
    }
    private fun isPasswordValid(password: String):Boolean {
        return password.isNotBlank()
    }
    private fun isUserNameValid(username: String): Boolean {
        return if(username.contains('@')) {
            PatternsCompat.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            false
        }
    }
}