package com.test.starwarstest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class FilmsData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Film>,
)