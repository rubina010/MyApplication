package com.example.myapplication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_book")
data class BookModel(
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "book_title")
        val book_title: String,

        @ColumnInfo(name = "book_author")
        val book_author: String,

        @ColumnInfo(name = "book_description")
        val book_description: String,

        @ColumnInfo(name = "book_picture")
        val book_picture: String
)