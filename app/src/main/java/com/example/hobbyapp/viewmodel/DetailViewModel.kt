package com.example.hobbyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hobbyapp.model.News
import com.example.hobbyapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailNewsViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val newsLD = MutableLiveData<News>()

    fun addTodo(list: List<News>) {
        launch {
            val db = buildDB(getApplication())

            db.newsDao().insertAll(*list.toTypedArray())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id:Int) {
        launch {
            val db = buildDB(getApplication())
            newsLD.postValue(db.newsDao().selectNews(id))
        }
    }



}