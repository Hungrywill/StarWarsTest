package com.test.starwarstest.utils

import android.content.Context
import com.test.starwarstest.R
import java.text.SimpleDateFormat
import java.util.*

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.no_network_connectivity)))
}

fun String.formatDate(formatInput: String, formatOutput: String): String {
    val parser = SimpleDateFormat(formatInput, Locale.FRANCE)
    val formatter = SimpleDateFormat(formatOutput, Locale.FRANCE)
    return formatter.format(parser.parse(this) ?: return "")
}

