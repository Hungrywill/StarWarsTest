package com.test.starwarstest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.starwarstest.data.Film
import com.test.starwarstest.data.FilmsData
import com.test.starwarstest.repository.FilmsRepository
import com.test.starwarstest.utils.AppResult
import kotlinx.coroutines.launch

class FilmsViewModel(private val repository: FilmsRepository) : ViewModel() {

    val filmsList = MutableLiveData<FilmsData>()
    val showError = MutableLiveData<String>()
    val film = MutableLiveData<Film>()

    fun getAllFilms() {
        viewModelScope.launch {
            when (val result = repository.getAllFilms()) {
                is AppResult.Success -> {
                    filmsList.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> {
                    showError.value = result.exception.message
                }
            }
        }
    }

    fun getFilm(id: Int) {
        viewModelScope.launch {
            when (val result = repository.getFilm(id)) {
                is AppResult.Success -> {
                    film.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> {
                    Log.e("error", result.exception.message.toString())
                    showError.value = result.exception.message
                }
            }
        }
    }


}