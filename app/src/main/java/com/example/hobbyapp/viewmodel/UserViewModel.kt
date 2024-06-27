package com.example.hobbyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hobbyapp.model.User
import com.example.hobbyapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    val userLD = MutableLiveData<User>()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun register(list: List<User>) {
        launch {
            val db = buildDB(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    fun login(username: String, password: String) {
        launch {
            val db = buildDB(getApplication())
            userLD.postValue(db.userDao().selectUser(username, password))
        }
    }

    fun selectUser(uuid: Int) {
        launch {
            val db = buildDB(getApplication())
            userLD.postValue(db.userDao().selectUserByID(uuid))
        }
    }

    fun update(nama_depan: String, nama_belakang: String, password: String, uuid: Int) {
        launch {
            val db = buildDB(getApplication())
            db.userDao().updateUser(nama_depan, nama_belakang, password, uuid)
        }
    }
}