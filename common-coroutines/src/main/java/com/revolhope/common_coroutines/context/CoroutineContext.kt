package com.revolhope.common_coroutines.context

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

enum class CoroutineContext(internal val dispatcher: CoroutineDispatcher) {
    IO(dispatcher = Dispatchers.IO),
    UI(dispatcher = Dispatchers.Main),
    DEFAULT(dispatcher = Dispatchers.Default);

    companion object {
        fun map(dispatcher: CoroutineDispatcher): CoroutineContext =
            values().first { it.dispatcher == dispatcher }
    }
}

fun CoroutineContext.get(): CoroutineDispatcher = this.dispatcher

val COROUTINE_IO: CoroutineContext by lazy { CoroutineContext.IO }
val COROUTINE_UI: CoroutineContext by lazy { CoroutineContext.UI }
val COROUTINE_DEFAULT: CoroutineContext by lazy { CoroutineContext.DEFAULT }

val DISPATCHER_IO: CoroutineDispatcher by lazy { COROUTINE_IO.get() }
val DISPATCHER_UI: CoroutineDispatcher by lazy { COROUTINE_UI.get() }
val DISPATCHER_DEFAULT: CoroutineDispatcher by lazy { COROUTINE_DEFAULT.get() }