package com.example.filminfo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filminfo.Model.Movie

class MainViewModel : ViewModel() {
    private val _movies = MutableLiveData<String>()
    val movies: LiveData<String> get() = _movies
}