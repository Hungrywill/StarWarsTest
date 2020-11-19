package com.test.starwarstest.utils

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Response
import java.io.IOException

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): AppResult.Error {
        val error = ApiErrorUtils.parseError(resp)
        return AppResult.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}

object ApiErrorUtils {

    fun parseError(response: Response<*>): APIError {

        val gson = GsonBuilder().create()
        val error: APIError

        try {
            error = gson.fromJson(response.errorBody()?.string(), APIError::class.java)
        } catch (e: IOException) {
            e.message?.let { Log.d(TAG, it) }
            return APIError()
        }
        return error
    }

}

data class APIError(val message: String) {
    constructor() : this("")
}

const val FORMAT_LONG_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val FORMAT_SHORT_SLASH_DATE = "yyyy/MM/dd"
const val FORMAT_SHORT_DATE = "yyyy-MM-dd"
const val EXTRA_POSITION = "POSITION"