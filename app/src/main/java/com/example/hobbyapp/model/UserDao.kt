package com.example.hobbyapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    fun selectUser(username: String, password: String): User

    @Query("UPDATE user SET username= :username, nama_depan= :nama_depan, nama_belakang= :nama_belakang, password= :password, email= :email WHERE uuid= :id")
    fun updateUser(username: String, nama_depan: String, nama_belakang: String, password: String, email: String, id: Int)
}