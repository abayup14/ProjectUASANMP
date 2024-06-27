package com.example.hobbyapp.view

import android.view.View
import com.example.hobbyapp.model.User

interface UpdateUserClick {
    fun updateUser(v: View, obj: User)
}