package com.example.hobbyapp.model

data class News (
    var id: Int,
    var judul: String,
    var deskripsi_singkat : String,
    var category: String,
    var pengarang: Int
)