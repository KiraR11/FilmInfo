package com.example.filminfo.View

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.filminfo.Data.ApiService
import com.example.filminfo.Data.Repository
import com.example.filminfo.R
import com.example.filminfo.ViewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editTextFilmJson = findViewById<TextView>(R.id.TextFilmJson)
        val LinearLayoutnotification = findViewById<View>(R.id.LinearLayoutnotification)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val snackbar = Snackbar.make(
            LinearLayoutnotification,
            "Ошибка подключения сети",
            Snackbar.LENGTH_INDEFINITE
        ).setActionTextColor(ContextCompat.getColor(this, R.color.base_yellow))

        val apiService = ApiService.create()
        var repository = Repository(apiService)
        viewModel = MainViewModel(repository) // TODO поменять на ViewModelProvider(this)[MainViewModel::class.java]

        snackbar.setAction("Повторить") {
            viewModel.loadMovies()
        }

        viewModel.loading.observe(this) { isLoading ->
            progressBar.isVisible = isLoading
            if (isLoading) {
                editTextFilmJson.isVisible = false
            }
        }

        viewModel.movies.observe(this) { result ->//viewLifecycleOwner
            result.onSuccess { movies ->
                editTextFilmJson.isVisible = true;
                editTextFilmJson.text = movies.toString()
            }.onFailure { exception ->
                snackbar.show()
            }
        }

        viewModel.loadMovies()
    }
}