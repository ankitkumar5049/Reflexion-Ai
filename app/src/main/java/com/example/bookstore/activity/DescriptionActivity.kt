package com.example.bookstore.activity

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.bookstore.R
import com.example.bookstore.database.MovieDatabase
import com.example.bookstore.database.MovieEntity

class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName :TextView
    lateinit var txtBookAuthor :TextView
    lateinit var txtBookPrice :TextView
    lateinit var txtBookRating :TextView
    lateinit var imgBookImage :ImageView
    lateinit var txtBookDesc : TextView
    lateinit var btnAddToFav :Button
    lateinit var progressBar : ProgressBar
    lateinit var progressLayout : RelativeLayout
    lateinit var toolbar: Toolbar

    var bookId : String? = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        txtBookDesc = findViewById(R.id.txtBookDec)
        btnAddToFav = findViewById(R.id.btnAddToFav)
        progressBar = findViewById(R.id.progressBar)
        progressLayout = findViewById(R.id.progressLayout)
        progressBar.visibility = View.VISIBLE
        progressLayout.visibility = View.VISIBLE
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Movie Details"

        if (intent!=null){
            bookId = intent.getStringExtra("book_id")
        }else{
            finish()
            Toast.makeText(this@DescriptionActivity,"Some unexpected error occurred",Toast.LENGTH_SHORT).show()
        }

        if(bookId=="100"){
            Toast.makeText(this@DescriptionActivity,"Some unexpected error occurred",Toast.LENGTH_SHORT).show()
        }

    }

    class DBAsyncTask(val context: Context, val movieEntity: MovieEntity, val mode:Int) : AsyncTask<Void, Void, Boolean>() {
        /*
        Mode 1 -> check if the book is favourite or not
        Mode 2 -> save the book into DB as favourite
        Mode 3 -> Remove the favourite book
         */
        val db = Room.databaseBuilder(context,MovieDatabase::class.java,"movie-db").build()

        override fun doInBackground(vararg p0: Void?): Boolean {
            when(mode){
                1->{
                    val movie: MovieEntity? = db.movieDao().getBookById(movieEntity.IMDBID.toString())
                    db.close()
                    return movie!= null
                }
                2->{
                    db.movieDao().insertBook(movieEntity)
                    db.close()
                    return true
                }
                3->{
                    db.movieDao().deleteBook(movieEntity)
                    db.close()
                    return true
                }
            }
            return false
        }
    }
}