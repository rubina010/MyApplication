package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.BookDao
import com.example.myapplication.entity.BookModel
import com.example.myapplication.ui_tv.database.dao.SongDao
import com.example.myapplication.ui_tv.entity.SongEntity

@Database(entities = [(BookModel::class), (SongEntity::class)], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun songDao(): SongDao
}