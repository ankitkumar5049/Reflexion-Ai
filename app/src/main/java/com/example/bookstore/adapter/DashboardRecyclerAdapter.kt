package com.example.bookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.model.Movie
import com.squareup.picasso.Picasso

class DashboardRecyclerAdapter(val context : Context, private val itemList : ArrayList<Movie>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){

    class DashboardViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val txtBookName : TextView = view.findViewById(R.id.txtBookName)
        val txtBookAuthor : TextView = view.findViewById(R.id.txtBookAuthor)
        val txtBookPrice : TextView = view.findViewById(R.id.txtBookPrice)
        val txtMovieTime : TextView = view.findViewById(R.id.txtMovieTime)
        val txtMovieYear : TextView = view.findViewById(R.id.txtMovieYear)
        val txtBookRating : TextView = view.findViewById(R.id.txtBookRating)
        val imgBookImage : ImageView = view.findViewById(R.id.imgBookImage)
        val btnAddToFav : ImageView = view.findViewById(R.id.imgHeart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val movie = itemList[position]
        holder.txtBookName.text = movie.title
        holder.txtBookAuthor.text = movie.director
        holder.txtBookPrice.text = movie.genres
        holder.txtBookRating.text = movie.rating
        holder.txtMovieTime.text = movie.runtime+" min"
        holder.txtMovieYear.text = "Y: "+movie.year
        Picasso.get().load(movie.moviePoster).error(R.drawable.default_book_cover).into(holder.imgBookImage)


        var tick = false
        holder.btnAddToFav.setOnClickListener {
            tick = if(tick){
                holder.btnAddToFav.setImageResource(R.drawable.ic_hollow_heart)
                Toast.makeText(context,"Removed from favorite", Toast.LENGTH_SHORT).show()
                false
            } else {
                holder.btnAddToFav.setImageResource(R.drawable.ic_heart_red)
                Toast.makeText(context,"Added to favorite", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}

