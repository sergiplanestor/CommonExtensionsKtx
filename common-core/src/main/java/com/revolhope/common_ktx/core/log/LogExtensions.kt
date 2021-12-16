package com.revolhope.common_ktx.core.log

fun <T : Any> T.err(message: String? = null, error: Throwable? = null) {
    log(level = LogLevel.E, message, error)
}

fun <T : Any> T.log(level: LogLevel, message: String? = null, error: Throwable? = null) {
    Logger.log(
        level = level,
        tag = this::class.simpleName,
        message = message,
        error = error
    )
}