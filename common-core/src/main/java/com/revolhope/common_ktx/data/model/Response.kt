package com.revolhope.common_ktx.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class Response<out T> : Parcelable {

    @Parcelize
    data class Success<out T>(val data: @RawValue T) : Response<T>()

    @Parcelize
    data class Failure<out T>(val cause: Throwable) : Response<T>()
}
