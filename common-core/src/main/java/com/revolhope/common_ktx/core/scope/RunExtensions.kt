package com.revolhope.common_ktx.core.scope

import com.revolhope.common_ktx.core.errors.runSafeOrNull

fun <T, R> T.runOrThrow(condition: T.() -> Boolean, block: T.() -> R): R =
    runOrNull(isSafeRun = true, condition(), block)!!

fun <T, R> T.runOrNull(isSafeRun: Boolean = false, condition: Boolean, block: T.() -> R?): R? =
    kotlin.run {
        when {
            condition && isSafeRun -> runSafeOrNull(block)
            condition -> block()
            else -> null
        }
    }

fun <T, R> T.runOrNull(
    isSafeRun: Boolean = false,
    condition: T.() -> Boolean,
    block: T.() -> R?
): R? =
    runOrNull(isSafeRun, condition(), block)

fun <T, R> T.runOrDefault(
    isSafeRun: Boolean = false,
    condition: Boolean,
    default: R,
    block: T.() -> R?
): R =
    runOrNull(isSafeRun, condition, block) ?: default

fun <T, R> T.runOrDefault(
    isSafeRun: Boolean = false,
    condition: T.() -> Boolean,
    default: R,
    block: T.() -> R?
): R =
    runOrDefault(isSafeRun, condition(), default, block)