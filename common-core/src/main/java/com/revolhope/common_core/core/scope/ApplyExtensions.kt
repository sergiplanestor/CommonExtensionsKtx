package com.revolhope.common_core.core.scope

import com.revolhope.common_core.core.errors.run_catching.runSafe

fun <T> T.applyIf(isRunSafe: Boolean = false, condition: Boolean = true, block: T.() -> Unit): T =
    apply {
        when {
            condition && isRunSafe -> runSafe(block)
            condition -> block()
        }
    }

fun <T> T.applyIf(
    isRunSafe: Boolean = false,
    condition: T.() -> Boolean = { true },
    block: T.() -> Unit
): T =
    applyIf(isRunSafe, condition = condition.invoke(this), block = block)