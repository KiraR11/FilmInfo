package com.example.filminfo.Model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("films.json")
    fun getMovies() : Call<MovieResponse>

    companion object {

        var BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"
        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}