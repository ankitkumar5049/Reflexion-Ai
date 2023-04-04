package com.example.bookstore.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val IMDBID: String,
    @ColumnInfo(name = "movie_name") val title : String,
    @ColumnInfo(name = "movie_director") val movieDirector : String,
    @ColumnInfo(name = "movie_cast") val movieCast : String,
    @ColumnInfo(name = "movie_rating") val movieRating : String,
    @ColumnInfo(name = "movie_image") val movieImage : String
)