package com.example.filminfo.View

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.filminfo.Model.ApiInterface
import com.example.filminfo.Model.Movie
import com.example.filminfo.Model.MovieResponse
import com.example.filminfo.R
import com.example.filminfo.ViewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextFilmJson = findViewById<TextView>(R.id.TextFilmJson)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue( object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    Log.d("testRetrofit", "Successful")
                    val movies = response.body()
                    editTextFilmJson.text = movies?.films?.toString()
                } else {
                    Log.d("testRetrofit", "Error: ${response.code()} - ${response.message()}")
                    editTextFilmJson.text = "Ошибка"
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("testRetrofit", "Failurefull")
                Log.d("testRetrofit", "Error: ${t.message}")
                editTextFilmJson.text = "Ошибка"
            }
        })
    }
}