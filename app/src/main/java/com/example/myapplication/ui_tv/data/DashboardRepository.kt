package com.example.myapplication.ui_tv.data

import androidx.lifecycle.LiveData
import com.example.myapplication.ui_tv.database.AppDatabase
import com.example.myapplication.ui_tv.entity.SongEntity
import timber.log.Timber
import javax.inject.Inject

class DashboardRepository @Inject constructor(val database: AppDatabase) {
    fun addSongs(songName: String, singer: String) = database.songDao().insertSong(SongEntity(4, songName, singer))
    fun getSongs():List<SongEntity> {
        Timber.i("databasesize ${database.songDao().getSongs()}")
        return database.songDao().getSongs()
    }
}