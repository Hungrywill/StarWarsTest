package com.test.starwarstest.repository

import com.test.starwarstest.data.Film
import com.test.starwarstest.data.FilmsData
import com.test.starwarstest.utils.AppResult

interface FilmsRepository {
    suspend fun getAllFilms() : AppResult<FilmsData>
    suspend fun getFilm(id : Int) : AppResult<Film>
}