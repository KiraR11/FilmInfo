package com.example.filminfo.data

import com.example.filminfo.model.FilmResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("films.json")
    suspend fun getMovies() : Response<FilmResponse>

    companion object {

        var BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"
        fun create() : ApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)

        }
    }
}