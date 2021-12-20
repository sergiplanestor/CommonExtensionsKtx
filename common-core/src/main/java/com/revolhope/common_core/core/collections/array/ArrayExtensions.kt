package com.revolhope.common_core.core.collections.array

inline fun <T> Array<out T>.firstOrDefault(default: T, predicate: (T) -> Boolean): T =
    firstOrNull(predicate) ?: default