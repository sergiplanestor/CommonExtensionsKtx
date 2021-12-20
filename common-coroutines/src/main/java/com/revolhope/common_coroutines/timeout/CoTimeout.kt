package com.revolhope.common_coroutines.timeout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

private const val MIN = 1500L       // 1.5s
private const val SHORT = 7500L     // 7.5s
private const val DEFAULT = 15000L  // 15s
private const val LONG = 30000L     // 30s
private const val INFINITE = -1L

@Parcelize
data class CoTimeout(
    val duration: Duration = Duration.Default,
    val onTimeout: @RawValue Throwable.() -> Unit = {}
): Parcelable {

    sealed class Duration(open val millis: kotlin.Long) : Parcelable {
        @Parcelize
        object Short : Duration(millis = SHORT)
        @Parcelize
        object Default : Duration(millis = DEFAULT)
        @Parcelize
        object Long : Duration(millis = LONG)
        @Parcelize
        object Infinite : Duration(millis = INFINITE)
        @Parcelize
        data class Custom(override val millis: kotlin.Long) :
            Duration(if (millis < MIN) MIN else millis)
    }

    companion object {
        val DefaultOnTimeoutIgnore: CoTimeout = CoTimeout()
        val DefaultOnTimeoutThrow: CoTimeout = CoTimeout { throw this }
    }
}
