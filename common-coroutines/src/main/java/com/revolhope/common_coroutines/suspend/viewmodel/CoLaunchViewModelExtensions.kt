package com.revolhope.common_coroutines.suspend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revolhope.common_coroutines.suspend.launchCoroutine
import com.revolhope.common_coroutines.timeout.CoTimeout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job


fun ViewModel.launchCoroutine(
    timeout: CoTimeout = CoTimeout.DefaultOnTimeoutThrow,
    block: suspend CoroutineScope.() -> Unit
): Job =
    launchCoroutine(viewModelScope, timeout, block)
