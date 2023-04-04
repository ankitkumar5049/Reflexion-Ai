package com.example.bookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.database.MovieEntity
import com.squareup.picasso.Picasso

class FavouriteRecyclerAdapter(val context: Context, private val movieList: List<MovieEntity>): RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.recycler_favourite_single_row,parent,false)
        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val movie = movieList[position]

        holder.txtBookName.text = movie.title
        holder.txtBookAuthor.text = movie.movieDirector
        holder.txtBookPrice.text = movie.movieCast
        holder.txtBookRating.text = movie.movieRating
        Picasso.get().load(movie.movieImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class FavouriteViewHolder(view:View):RecyclerView.ViewHolder(view){
        val txtBookName : TextView = view.findViewById(R.id.txtFavBookTitle)
        val txtBookAuthor: TextView = view.findViewById(R.id.txtFavBookAuthor)
        val txtBookPrice : TextView = view.findViewById(R.id.txtFavBookPrice)
        val txtBookRating : TextView = view.findViewById(R.id.txtFavBookRating)
        val imgBookImage : ImageView = view.findViewById(R.id.imgFavBookImage)
    }
}