package ru.starfactory.pixel.domain.theme

import kotlinx.coroutines.flow.Flow
import ru.starfactory.pixel.repository.theme.ThemeRepository


interface ThemeInteractor {
    fun observeCurrentTheme(): Flow<Theme>
    suspend fun setCurrentTheme(theme: Theme)
}

class ThemeInteractorImpl(
    private val themeRepository: ThemeRepository
) : ThemeInteractor {
    override fun observeCurrentTheme(): Flow<Theme> = themeRepository.observeCurrentTheme()

    override suspend fun setCurrentTheme(theme: Theme) {
        themeRepository.setCurrentTheme(theme)
    }
}