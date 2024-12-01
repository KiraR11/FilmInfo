package com.example.filminfo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filminfo.data.Repository
import com.example.filminfo.model.Film
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingDataViewModel(private val repository: Repository) : ViewModel() {

    private val _films = MutableLiveData<Result<List<Film>>>()
    val films: LiveData<Result<List<Film>>> get() = _films

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun loadMovies() {
        viewModelScope.launch {
            _loading.value = true
            delay(1000)
            val result = repository.fetchFilms()
            _films.value = result
            _loading.value = false
        }
    }
}