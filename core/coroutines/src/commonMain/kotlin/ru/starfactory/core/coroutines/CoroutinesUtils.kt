package ru.starfactory.core.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

private const val DEFAULT_TIMEOUT = 1000L

fun <T> Flow<T>.shareDefault(scope: CoroutineScope) = shareIn(
    scope,
    SharingStarted.WhileSubscribed(DEFAULT_TIMEOUT, 0),
    replay = 1,
)
