package com.example.hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hobbyapp.util.DB_NAME
import com.example.hobbyapp.util.MIGRATION_USER_1_2
import com.example.hobbyapp.util.buildDB

@Database(entities = arrayOf(User::class), version = 2)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: UserDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = buildDB(context)

        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }

}