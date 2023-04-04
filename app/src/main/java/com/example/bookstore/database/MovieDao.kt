package com.example.bookstore.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    fun insertBook(MovieEntity : MovieEntity)

    @Delete
    fun deleteBook(MovieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllBooks():List<MovieEntity>

    @Query("SELECT * FROM movies WHERE IMDBID = :movieId")
    fun getBookById(movieId : String ): MovieEntity
}