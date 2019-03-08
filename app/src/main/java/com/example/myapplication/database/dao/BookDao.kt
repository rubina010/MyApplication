package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.entity.BookModel

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(bookModel: BookModel)

    @Query("SELECT * FROM tbl_book ORDER BY book_title")
    fun getAllBook(): LiveData<List<BookModel>>
}