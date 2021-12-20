package com.revolhope.common_core.core.errors.throwable.errors.io.network

import com.revolhope.common_core.core.errors.throwable.errors.Error
import kotlinx.parcelize.Parcelize

abstract class Network(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Error.IO(message, cause) {
    @Parcelize
    object Connection : Network()

    @Parcelize
    object Capabilities : Network()

    @Parcelize
    data class InvalidRequest(
        val req: String? = null,
        override val cause: Throwable? = null
    ) : Network(cause = cause)

    @Parcelize
    data class InvalidResponse(
        val res: String? = null,
        override val cause: Throwable? = null
    ) : Network(cause = cause)
}