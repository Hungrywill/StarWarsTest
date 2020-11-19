package com.test.starwarstest.repository

import android.content.Context
import com.test.starwarstest.api.FilmsApi
import com.test.starwarstest.data.Film
import com.test.starwarstest.data.FilmsData
import com.test.starwarstest.utils.AppResult
import com.test.starwarstest.utils.NetworkManager.isOnline
import com.test.starwarstest.utils.Utils.handleApiError
import com.test.starwarstest.utils.Utils.handleSuccess
import com.test.starwarstest.utils.noNetworkConnectivityError

class FilmsRepositoryImpl(
    private val api: FilmsApi,
    private val context: Context,
) : FilmsRepository {

    override suspend fun getAllFilms(): AppResult<FilmsData> {
        return if (isOnline(context)) {
            try {
                val response = api.getAllFilms()
                if (response.isSuccessful) {
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            context.noNetworkConnectivityError()
        }
    }

    override suspend fun getFilm(id: Int): AppResult<Film> {
        return if (isOnline(context)) {
            try {
                val response = api.getFilm(id)
                if (response.isSuccessful) {
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            context.noNetworkConnectivityError()
        }
    }

}