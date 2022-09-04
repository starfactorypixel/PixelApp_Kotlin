package ru.starfactory.pixel.theming.domain

import kotlinx.coroutines.flow.Flow
import ru.starfactory.pixel.theming.repository.ThemeRepository

interface ThemeInteractor {
    fun observeCurrentTheme(): Flow<Theme>
    suspend fun setCurrentTheme(theme: Theme)
}

internal class ThemeInteractorImpl(
    private val themeRepository: ThemeRepository
) : ThemeInteractor {
    override fun observeCurrentTheme(): Flow<Theme> = themeRepository.observeCurrentTheme()

    override suspend fun setCurrentTheme(theme: Theme) {
        themeRepository.setCurrentTheme(theme)
    }
}
