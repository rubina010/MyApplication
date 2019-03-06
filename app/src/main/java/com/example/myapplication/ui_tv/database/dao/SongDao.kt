package com.example.myapplication.ui_tv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.ui_tv.entity.SongEntity

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(songEntity: SongEntity)

    @Query("SELECT * FROM tbl_songs")
    fun getSongs(): List<SongEntity>
}