package com.example.projectmanagestaff.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.projectmanagestaff.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun findById(id:Int): Flow<User?>
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun login(email:String,password: String):Flow<User?>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User):Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int
}