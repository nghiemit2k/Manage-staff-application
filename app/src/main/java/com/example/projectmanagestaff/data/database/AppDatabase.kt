package com.example.projectmanagestaff.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.projectmanagestaff.data.dao.UserDao
import com.example.projectmanagestaff.data.model.User
import java.util.Date


@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? =null
        fun getDatabase(context: Context):AppDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                instance = db
                db
            }
        }
    }
}
class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter fun dateToTimestamp(date: Date):Long? {
        return date?.time
    }
}
