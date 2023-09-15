package com.example.projectmanagestaff.data.repository

import com.example.projectmanagestaff.data.dao.UserDao
import com.example.projectmanagestaff.data.model.User
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(private val userDao: UserDao): ProfileRepository {
    override fun login(email: String, password: String): Flow<User?> {
        return userDao.login(email,password)
    }

    override fun findUserById(id: Int): Flow<User?> {
        return userDao.findById(id)
    }
}