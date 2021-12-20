package com.revolhope.common_core.core.scope

infix fun <T> T?.default(other: T): T =
    this ?: other

infix fun <T> T?.default(block: () -> T): T =
    this ?: block()

infix fun <T> (() -> T?).default(other: T): T =
    this.invoke() default other

infix fun <T> (() -> T?).default(block: () -> T): T =
    this.invoke() default block

infix fun <T> T?.throwOnNull(throwable: Throwable): T =
    this default { throw throwable }

suspend infix fun <T> T?.defaultSuspended(block: suspend () -> T): T =
    this default block()

suspend infix fun <T> (suspend () -> T?).defaultSuspended(other: T): T =
    this.invoke() default other

suspend infix fun <T> (suspend () -> T?).defaultSuspended(block: suspend () -> T): T =
    this.invoke() defaultSuspended block