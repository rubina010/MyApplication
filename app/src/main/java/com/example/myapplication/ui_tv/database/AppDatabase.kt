package com.example.myapplication.ui_tv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.ui_tv.database.dao.SongDao
import com.example.myapplication.ui_tv.entity.SongEntity

@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}