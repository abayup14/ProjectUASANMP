package com.example.hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hobbyapp.util.buildDb

@Database(entities = arrayOf(News::class), version =  3)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = buildDb(context)
        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }

}