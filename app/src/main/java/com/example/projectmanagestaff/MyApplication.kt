package com.example.projectmanagestaff

import android.app.Application
import com.example.projectmanagestaff.data.database.AppDatabase
import com.example.projectmanagestaff.data.repository.ProfileRepositoryImpl

class MyApplication: Application() {
    private val database by lazy { AppDatabase.getDatabase(applicationContext) }
    val repository by lazy { ProfileRepositoryImpl(database.getUserDao()) }

}