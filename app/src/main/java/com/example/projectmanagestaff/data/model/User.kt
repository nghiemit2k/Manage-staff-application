package com.example.projectmanagestaff.data.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo("full_name")
    val fullName: String = "",
    val email: String ="",
    val password: String ="",
    val gender: String="",
    @ColumnInfo("phone_number") val phoneNumber: String? = null,
    val avatar: Int?=null,
    @ColumnInfo("birth_date")val birthdate: Date? =null
)
