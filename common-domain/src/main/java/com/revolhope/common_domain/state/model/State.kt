package com.revolhope.common_domain.state.model

import android.os.Parcelable
import com.revolhope.common_core.core.primitive.string.STRING_EMPTY
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class State<out T> : Parcelable {

    @Parcelize
    data class Ready<out T>(val data: @RawValue T): State<T>()

    @Parcelize
    data class Error<out T>(val cause: Throwable): State<T>()

    @Parcelize
    data class Loading<out T>(
        val id: String = STRING_EMPTY,
        val timeout: Long = TIMEOUT_INFINITE
    ): State<T>() {
        val hasTimeout: Boolean get() = timeout > TIMEOUT_MIN

        companion object {
            const val TIMEOUT_INFINITE = -1L
            const val TIMEOUT_MIN = 500L // 0.5s
        }
    }

    val isReady: Boolean get() = this is Ready

    val isError: Boolean get() = this is Error

    val isLoading: Boolean get() = this is Loading

    val isNotLoading: Boolean get() = isLoading.not()

    companion object {
        fun <T> ready(data: T): State<T> = Ready(data)
        fun <T> error(cause: Throwable): State<T> = Error(cause)
        fun <T> loading(id: String, timeout: Long): State<T> = Loading(id, timeout)
    }
}