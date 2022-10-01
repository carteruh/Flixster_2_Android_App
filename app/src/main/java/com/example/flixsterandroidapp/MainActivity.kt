package com.example.flixsterandroidapp

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    // Define the global properties of the MainActivity class
    private var movies: ArrayList<com.example.flixsterandroidapp.Movie> = ArrayList()
    private var api = "950bb4aec85322ea3985d523b139eee8"
    private var url = "https://api.themoviedb.org/3/movie/now_playing"

    // Define the onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the RecyclerView
        val movieList = findViewById<RecyclerView>(R.id.movieList)

        // Create the AsyncHttpClient
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = api
        params["language"] = "en-US"
        params["page"] = "1"

        // Make the network request
        client.get(url, params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                // Get the JSON object
                val jsonObject = json?.jsonObject

                // Parse the JSON object
                val results = jsonObject?.getJSONArray("results")

                // Get the movies from the results
                for (i in 0 until results!!.length()) {
                    val movie = results.getJSONObject(i)
                    val movieObject = Movie(movie.getString("title"), movie.getString("overview"), "https://image.tmdb.org/t/p/w500/" + movie.getString("poster_path"))
                    movies.add(movieObject)

                    // Log the movie
                    Log.v("Json", jsonObject.toString())
                    Log.v("Parsed Movie", movie.getString("title"))
                    Log.v("Poster URL", "https://image.tmdb.org/t/p/w500/" + movie.getString("poster_path"))
                }

                // Create a new adapter
                val adapter = MovieAdapter(movies)

                // Attach the adapter to the RecyclerView
                movieList.adapter = adapter

                // Set the layout manager to position the items
                movieList.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                // Print the error to Logcat
                Log.v("API Error", throwable.toString())
            }
        })
    }
}