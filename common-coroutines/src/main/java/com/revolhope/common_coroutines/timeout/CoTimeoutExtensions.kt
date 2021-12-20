package com.revolhope.common_coroutines.timeout


fun CoTimeout.isDurationValid(): Boolean =
    this.duration !is CoTimeout.Duration.Infinite