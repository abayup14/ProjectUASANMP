package com.example.hobbyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hobbyapp.model.News
//import com.example.hobbyapp.model.NewsDatabase
import com.example.hobbyapp.model.UserDatabase
import com.example.hobbyapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ListViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val newsLD = MutableLiveData<List<News>>()
    val newsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        newsLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())

            newsLD.postValue(db.newsDao().selectAllNews())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(news: News) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
//            db.todoDao().updateDone(todo.uuid)

            newsLD.postValue(db.newsDao().selectAllNews())
        }
    }

}



