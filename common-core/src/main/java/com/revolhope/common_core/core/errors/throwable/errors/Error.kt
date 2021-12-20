package com.revolhope.common_core.core.errors.throwable.errors

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

abstract class Error(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(), Parcelable {

    @Parcelize
    object Unexpected : Error()

    @Parcelize
    object Timeout : Error()

    abstract class IO(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : Error(message, cause)
}