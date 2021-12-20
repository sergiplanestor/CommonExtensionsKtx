package com.revolhope.common_core.core.scope


fun <T, R> T?.doOnNull(isRunSafe: Boolean = false, action: () -> R): R? =
    runOrNull(isSafeRun = isRunSafe, condition = this == null) { action() }

fun <T, R> T?.doOnNotNull(isRunSafe: Boolean = false, action: () -> R): R? =
    runOrNull(isSafeRun = isRunSafe, condition = this != null) { action() }
