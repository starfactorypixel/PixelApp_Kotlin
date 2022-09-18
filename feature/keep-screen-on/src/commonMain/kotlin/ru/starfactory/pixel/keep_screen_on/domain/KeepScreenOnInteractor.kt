package ru.starfactory.pixel.keep_screen_on.domain

import kotlinx.coroutines.flow.Flow
import ru.starfactory.pixel.keep_screen_on.repository.KeepScreenOnRepository

interface KeepScreenOnInteractor {
    fun observeIsScreenAlwaysOn(): Flow<Boolean>
    suspend fun setIsScreenAlwaysOn(isAlwaysOn: Boolean)
}

internal class KeepScreenOnInteractorImpl(
    private val keepScreenOnRepository: KeepScreenOnRepository,
) : KeepScreenOnInteractor {
    override fun observeIsScreenAlwaysOn(): Flow<Boolean> = keepScreenOnRepository.observeIsKeepScreenOn()

    override suspend fun setIsScreenAlwaysOn(isAlwaysOn: Boolean) {
        keepScreenOnRepository.setIsKeepScreenOn(isAlwaysOn)
    }
}
