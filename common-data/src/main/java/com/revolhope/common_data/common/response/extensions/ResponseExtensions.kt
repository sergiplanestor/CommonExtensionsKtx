package com.revolhope.common_data.common.response.extensions

import com.revolhope.common_data.common.response.model.Response

fun <T, R> Response<T>.fold(
    doOnSuccess: T.() -> R? = { null },
    doOnError: Throwable.() -> R? = { null }
): R? =
    when(this) {
        is Response.Success -> doOnSuccess(data)
        is Response.Failure -> doOnError(cause)
    }

fun <T> Response<T>.dataOrNull(): T? =
    (this as? Response.Success<T>)?.data

fun <T> Response<T>.errorOrNull(): Throwable? =
    (this as? Response.Failure<T>)?.cause

fun <T, R> Response<T>.map(mapper: T.() -> R): Response<R> =
    when(this) {
        is Response.Success -> Response.Success(mapper(data))
        is Response.Failure -> Response.Failure(cause)
    }

fun <T, R> Response<T>.onSuccessThen(action: T.() -> Response<R>): Response<R>? =
    fold(doOnSuccess = action)

fun <T> Response<T>.applyOnSuccess(action: T.() -> Unit): Response<T> =
    apply { (this as? Response.Success<T>)?.data?.action() }

fun <T> Response<T>.applyOnFailure(action: Throwable.() -> Unit): Response<T> =
    apply { (this as? Response.Failure<T>)?.cause?.action() }