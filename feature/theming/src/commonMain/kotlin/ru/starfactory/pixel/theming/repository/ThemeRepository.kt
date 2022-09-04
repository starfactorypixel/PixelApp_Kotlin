package ru.starfactory.pixel.theming.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.starfactory.core.key_value_storage.service.KeyValueStorageService
import ru.starfactory.pixel.theming.domain.Theme

internal interface ThemeRepository {
    fun observeCurrentTheme(): Flow<Theme>
    suspend fun setCurrentTheme(theme: Theme)
}

internal class ThemeRepositoryImpl(keyValueStorageService: KeyValueStorageService) : ThemeRepository {
    private val storage = keyValueStorageService.createStorage("feature-theming")

    val theme = storage.getStringOrNullFlow(THEME_KEY)
        .map {
            runCatching { Theme.valueOf(it!!) }
                .getOrElse { Theme.SYSTEM }
        }

    override fun observeCurrentTheme(): Flow<Theme> = theme

    override suspend fun setCurrentTheme(theme: Theme) {
        storage.putString(THEME_KEY, theme.name)
    }

    companion object {
        private const val THEME_KEY = "theme"
    }
}
