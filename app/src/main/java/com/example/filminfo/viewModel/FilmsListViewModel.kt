package com.example.filminfo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filminfo.model.Film

class FilmsListViewModel(var allFilms: List<Film>) : ViewModel() {

    init{
        allFilms = allFilms.sortedBy { it.localizedName}
    }

    private val _films = MutableLiveData(allFilms)
    val films: LiveData<List<Film>> = this._films

     val genres: List<String> = allFilms.flatMap { it.genres!! }.distinct().sorted()

    fun setFilmByGenre(genre : String?){
        _films.value = if(genre == null) allFilms else allFilms.filter { film -> film.genres!!.contains(genre)}
    }
}