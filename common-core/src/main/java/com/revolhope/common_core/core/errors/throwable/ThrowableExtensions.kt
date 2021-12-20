package com.revolhope.common_core.core.errors.throwable

import com.revolhope.common_core.core.errors.throwable.errors.Error
import com.revolhope.common_core.core.scope.default
import kotlin.jvm.Throws

fun Throwable?.orAny(message: String? = "Any throwable"): Throwable =
    this default RuntimeException(message)

fun Throwable?.orUnexpected(): Throwable =
    this default Error.Unexpected

@Throws(Throwable::class)
fun Throwable?.throwThisOrAny(message: String? = "Any throwable") {
    throw this.orAny()
}

@Throws(Throwable::class)
fun Throwable?.throwThisOrUnexpected() {
    throw this.orUnexpected()
}