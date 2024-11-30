package com.example.filminfo.data

import com.example.filminfo.model.Film

class Repository(private val apiService: ApiService) {

    suspend fun fetchMovie(): Result<List<Film>> {
        return try {
            val response = apiService.getMovies()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.films)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception("Error: ${response.code()} - $errorBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}