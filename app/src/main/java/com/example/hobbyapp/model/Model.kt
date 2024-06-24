package com.example.hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "nama_depan")
    var nama_depan: String,
    @ColumnInfo(name = "nama_belakang")
    var nama_belakang: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "email")
    var email: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
