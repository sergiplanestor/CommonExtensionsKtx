package com.revolhope.common_domain.state.extensions

import com.revolhope.common_domain.state.model.State

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

fun <T> State<T>.applyOnReady(action: T.() -> Unit): State<T> =
    apply { (this as? State.Ready<T>)?.data?.action() }

fun <T> State<T>.applyOnError(action: Throwable.() -> Unit): State<T> =
    apply { (this as? State.Error<T>)?.cause?.action() }

fun <T> State<T>.applyOnLoading(action: (String, Long) -> Unit): State<T> =
    apply { (this as? State.Loading<T>)?.run { action(id, timeout) } }