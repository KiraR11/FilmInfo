package com.example.filminfo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filminfo.Data.Repository
import com.example.filminfo.Model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _movies = MutableLiveData<Result<List<Movie>>>()
    val movies: LiveData<Result<List<Movie>>> get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun loadMovies() {
        viewModelScope.launch {
            _loading.value = true
            delay(1000)
            val result = repository.fetchMovie()
            _movies.value = result
            _loading.value = false
        }
    }
}