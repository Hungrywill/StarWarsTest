package com.test.starwarstest.api

import com.test.starwarstest.data.Film
import com.test.starwarstest.data.FilmsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApi {
    @GET("/api/films")
    suspend fun getAllFilms(): Response<FilmsData>

    @GET("/api/films/{id}")
    suspend fun getFilm(@Path("id") id : Int): Response<Film>
}