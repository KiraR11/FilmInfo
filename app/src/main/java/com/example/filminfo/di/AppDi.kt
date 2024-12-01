package com.example.filminfo.di

import com.example.filminfo.data.ApiService
import com.example.filminfo.data.Repository
import com.example.filminfo.model.Film
import com.example.filminfo.viewModel.FilmInfoViewModel
import com.example.filminfo.viewModel.FilmsListViewModel
import com.example.filminfo.viewModel.LoadingDataViewModel
import org.koin.dsl.module
import  org.koin.core.module.dsl.viewModel

val appModule = module {
    viewModel<FilmInfoViewModel> { (film: Film) -> FilmInfoViewModel(film) }
    viewModel<FilmsListViewModel> { (films: List<Film>) -> FilmsListViewModel(films) }

    single { ApiService.create() }
    single { Repository(get()) }
    viewModel { LoadingDataViewModel(get()) }
}