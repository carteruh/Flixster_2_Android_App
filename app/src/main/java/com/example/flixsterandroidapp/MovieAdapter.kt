package com.example.flixsterandroidapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter (private var movies: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    // Define the view holder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define the properties of the ViewHolder class
        val movieName: TextView
        val movieDescription: TextView
        val moviePoster: ImageView

        init {
            // Get the movie name, movie description, and movie poster
            movieName = itemView.findViewById(R.id.movieName)
            movieDescription = itemView.findViewById(R.id.movieDescription)
            moviePoster = itemView.findViewById(R.id.moviePoster)
        }
    }

    // Override the onCreateViewHolder method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.movie_item, parent, false)
        return ViewHolder(itemView)
    }

    // Override the onBindViewHolder method
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the movie at the current position
        val movie = movies[position]

        // Set the movie name, movie description, and movie poster
        viewHolder.movieName.setText(movie.movieName)
        viewHolder.movieDescription.setText(movie.movieDescription)

        // Use Glide to load the movie poster
        Glide.with(viewHolder.moviePoster.context).load(movie.moviePoster).override(400, 600).into(viewHolder.moviePoster)
    }

    // Override the getItemCount method
    override fun getItemCount(): Int {
        return this.movies.size
    }
}