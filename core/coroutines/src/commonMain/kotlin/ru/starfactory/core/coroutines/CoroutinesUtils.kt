package ru.starfactory.core.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

fun <T> Flow<T>.shareDefault(scope: CoroutineScope) = shareIn(
    scope,
    SharingStarted.WhileSubscribed(1000, 0),
    replay = 1,
)
