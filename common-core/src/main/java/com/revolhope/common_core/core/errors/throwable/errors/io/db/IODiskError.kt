package com.revolhope.common_core.core.errors.throwable.errors.io.db

import com.revolhope.common_core.core.errors.throwable.errors.Error
import kotlinx.parcelize.Parcelize

abstract class Disk(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Error.IO(message, cause) {
    @Parcelize
    object Access : Disk()

    @Parcelize
    object Capabilities : Disk()
}