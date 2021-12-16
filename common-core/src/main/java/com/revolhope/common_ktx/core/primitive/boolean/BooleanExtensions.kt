package com.revolhope.common_ktx.core.primitive.boolean

import com.revolhope.common_ktx.core.scope.runOrNull

fun Boolean?.orTrue(): Boolean =
    this ?: true

fun Boolean?.orFalse(): Boolean =
    this ?: false

fun <R> Boolean?.fold(
    isSafeRun: Boolean = false,
    doOnTrue: () -> R?,
    doOnFalse: () -> R?
): R? =
    this.doOnTrue(isSafeRun, doOnTrue) ?: this.doOnFalse(isSafeRun, doOnFalse)

fun <R> Boolean?.doOnTrue(isSafeRun: Boolean = false, action: () -> R?): R? =
    runOrNull(isSafeRun = isSafeRun, condition = this == true) { action() }

fun <R> Boolean?.doOnFalse(isSafeRun: Boolean = false, action: () -> R?): R? =
    runOrNull(isSafeRun = isSafeRun, condition = this == false) { action() }