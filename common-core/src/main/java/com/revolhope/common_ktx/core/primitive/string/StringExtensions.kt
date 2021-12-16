package com.revolhope.common_ktx.core.primitive.string

import com.revolhope.common_ktx.core.errors.runSafeOrDefault
import com.revolhope.common_ktx.core.errors.runSafeOrNull

fun CharSequence?.isNotNullOrBlank(): Boolean =
    isNullOrBlank().not()

fun CharSequence?.orEmpty(): CharSequence =
    this ?: STRING_EMPTY

fun <T> CharSequence?.doOnNotNullOrBlank(action: CharSequence.() -> T): T? =
    runSafeOrNull {
        takeIf { it.isNotNullOrBlank() }?.action()
    }

fun <T> CharSequence.doOnBlank(action: CharSequence.() -> T): T? =
    runSafeOrNull {
        takeIf { it.isBlank() }?.action()
    }

fun <T> String.remove(value: T?, delegate: (T, String) -> String): String =
    runSafeOrDefault(default = this) {
        value?.run { delegate.invoke(this, STRING_EMPTY) } ?: this
    }

fun String.remove(str: String?, isLazy: Boolean = false): String =
    remove(value = str, delegate = if (isLazy) ::replaceFirst else ::replace)

fun String.remove(regex: Regex?, isLazy: Boolean = false): String =
    remove(value = regex, delegate = if (isLazy) ::replaceFirst else ::replace)
