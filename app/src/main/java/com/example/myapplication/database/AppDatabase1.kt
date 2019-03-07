package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.BookDao
import com.example.myapplication.entity.BookModel

@Database(entities = [(BookModel::class)], version = 1, exportSchema = false)
abstract class AppDatabase1 : RoomDatabase() {
    abstract fun bookDao(): BookDao
}