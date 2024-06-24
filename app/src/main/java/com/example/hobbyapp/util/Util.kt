package com.example.hobbyapp.util

import android.content.Context
import androidx.room.Room
import com.example.hobbyapp.model.UserDatabase


val DB_NAME = "project_uas_anmp"

fun buildDB(context: Context): UserDatabase {
    val db = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        DB_NAME
    )
        .build()
    return db
}