package com.revolhope.common_ktx.core.log

import android.util.Log
import com.revolhope.common_ktx.BuildConfig
import kotlin.random.Random

object Logger {

    private const val TAG = "Logger_"
    private const val TAG_ERROR = "${TAG}ERR"
    private const val TAG_VERBOSE = "${TAG}VRB"
    private const val TAG_DEBUG = "${TAG}DBG"
    private const val TAG_WARNING = "${TAG}WRN"
    private const val MSG_TEMPLATE =
        " --> {{START}} class {{ %1\$s }} says msg(id: %2\$s): {{ %3\$s }} {{END}}"
    private const val MSG_EMPTY = "NO_MSG_SPECIFIED"
    private const val TAG_EMPTY = "UNKNOWN_TAG"

    private var msgId: Long = Random.nextLong()
        get() = field.also { field++ }

    fun err(
        tag: String? = null,
        message: String? = null,
        error: Throwable? = null
    ) {
        log(LogLevel.E, tag, message, error)
    }

    fun log(
        level: LogLevel,
        tag: String? = null,
        message: String? = null,
        error: Throwable? = null
    ) {
        if (BuildConfig.DEBUG) {
            val msg = message(tag, message)
            when (level) {
                LogLevel.D -> Log.d(TAG_DEBUG, msg, error)
                LogLevel.W -> Log.w(TAG_WARNING, msg, error)
                LogLevel.E -> Log.e(TAG_ERROR, msg, error)
                LogLevel.V -> Log.v(TAG_VERBOSE, msg, error)
            }
        }
    }

    private fun message(tag: String?, message: String?): String =
        String.format(
            MSG_TEMPLATE,
            tag.getOrDefault(TAG_EMPTY),
            msgId.toString(),
            message.getOrDefault(MSG_EMPTY)
        )

    private fun String?.getOrDefault(default: String): String =
        if (isNullOrBlank()) default else this

}