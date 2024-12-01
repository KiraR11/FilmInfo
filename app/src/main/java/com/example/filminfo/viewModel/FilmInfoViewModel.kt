package com.example.filminfo.viewModel

import androidx.lifecycle.ViewModel
import com.example.filminfo.model.Film
import java.math.BigDecimal
import java.math.RoundingMode

class FilmInfoViewModel(val filmData: Film) : ViewModel() {

    fun getGenresAndYearFilmAsString(): String
    {
        val genres = if(!filmData.genres.isNullOrEmpty()) filmData.genres.joinToString(", ").plus(", ") else ""
        val year = filmData.year.toString().plus(" год")

        return genres.plus(year)
    }

    fun getRatingFilmAsString(): String = BigDecimal(filmData.rating).setScale(1, RoundingMode.HALF_UP).toString()
}