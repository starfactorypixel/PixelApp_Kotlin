package ru.starfactory.pixel.keep_screen_on.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface KeepScreenOnInteractor {
    fun observeIsScreenAlwaysOn(): Flow<Boolean>
    suspend fun setIsScreenAlwaysOn(isAlwaysOn: Boolean)
}

internal class KeepScreenOnInteractorImpl : KeepScreenOnInteractor {
    private val isScreenAlwaysOn = MutableStateFlow(false)

    override fun observeIsScreenAlwaysOn(): Flow<Boolean> = isScreenAlwaysOn

    override suspend fun setIsScreenAlwaysOn(isAlwaysOn: Boolean) {
        isScreenAlwaysOn.update { isAlwaysOn }
    }
}
