package com.revolhope.commonextensionsktx.feature.log

import android.util.Log

enum class LogLevel(val value: Int) {
    E(Log.ERROR),
    V(Log.VERBOSE),
    D(Log.DEBUG),
    W(Log.WARN)
}