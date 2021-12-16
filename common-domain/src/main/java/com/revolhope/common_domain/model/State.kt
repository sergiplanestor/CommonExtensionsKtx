package com.revolhope.common_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class State<out T> : Parcelable {

    @Parcelize
    data class Ready<out T>(val data: @RawValue T): State<T>()

    @Parcelize
    data class Error<out T>(val cause: Throwable): State<T>()

    @Parcelize
    data class Loading<out T>(val id: String, val timeout: Long): State<T>()

    val isReady: Boolean get() = this is Ready

    val isError: Boolean get() = this is Error

    val isLoading: Boolean get() = this is Loading

    val isNotLoading: Boolean get() = isLoading.not()
}

fun <T, R> State<T>.fold(
    doOnReady: T.() -> R? = { null },
    doOnError: Throwable.() -> R? = { null },
    doOnLoading: (String, Long) -> R? = { _, _ -> null }
): R? =
    when(this) {
        is State.Ready -> doOnReady(data)
        is State.Error -> doOnError(cause)
        is State.Loading -> doOnLoading(id, timeout)
    }

fun <T> State<T>.dataOrNull(): T? =
    (this as? State.Ready<T>)?.data

fun <T> State<T>.errorOrNull(): Throwable? =
    (this as? State.Error<T>)?.cause

fun <T, R> State<T>.map(mapper: T.() -> R): State<R> =
    when(this) {
        is State.Ready -> State.Ready(mapper(data))
        is State.Error -> State.Error(cause)
        is State.Loading -> State.Loading(id, timeout)
    }

fun <T, R> State<T>.onReadyThen(action: T.() -> State<R>): State<R>? =
    fold(doOnReady = action)
