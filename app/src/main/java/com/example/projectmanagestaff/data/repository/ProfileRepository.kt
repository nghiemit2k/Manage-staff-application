package com.example.projectmanagestaff.data.repository

import com.example.projectmanagestaff.data.model.User
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {
    fun login(email: String,password: String): Flow<User?>
    fun findUserById(id: Int): Flow<User?>
}