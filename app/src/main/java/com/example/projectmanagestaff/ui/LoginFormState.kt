package com.example.projectmanagestaff.ui

data class LoginFormState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isCorrect: Boolean = false
)
