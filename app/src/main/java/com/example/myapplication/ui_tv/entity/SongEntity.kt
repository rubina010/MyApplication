package com.example.myapplication.ui_tv.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_songs")
data class SongEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "SongTitle")
        val songName: String,
        @ColumnInfo(name = "Singer")
        val singerName: String
)