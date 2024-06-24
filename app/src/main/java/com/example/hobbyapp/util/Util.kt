package com.example.hobbyapp.util

import android.content.Context
import androidx.room.Room
import com.example.hobbyapp.model.NewsDatabase
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

fun buildDb(context: Context):NewsDatabase {
    val db = Room.databaseBuilder(context,
        NewsDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()

    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE news ADD COLUMN kategori STRING DEFAULT '' not null")
    }
}