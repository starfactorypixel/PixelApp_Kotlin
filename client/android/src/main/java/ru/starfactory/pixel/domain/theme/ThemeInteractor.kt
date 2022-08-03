package ru.starfactory.pixel.domain.theme

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface ThemeInteractor {
    fun observeCurrentTheme(): Flow<Theme>
    suspend fun setCurrentTheme(theme: Theme)
}

class ThemeInteractorImpl : ThemeInteractor {
    override fun observeCurrentTheme(): Flow<Theme> {
        return flowOf(Theme.SYSTEM)
    }

    override suspend fun setCurrentTheme(theme: Theme) {
        TODO("Not yet implemented")
    }
}