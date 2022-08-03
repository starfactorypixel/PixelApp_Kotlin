package ru.starfactory.pixel.repository.theme

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.starfactory.pixel.domain.theme.Theme

interface ThemeRepository {
    fun observeCurrentTheme(): Flow<Theme>
    suspend fun setCurrentTheme(theme: Theme)
}

class ThemeRepositoryImpl(val context: Context) : ThemeRepository {
    val Context.store by preferencesDataStore("theme-repository")

    override fun observeCurrentTheme(): Flow<Theme> {
        return context.store.data.map {
            val raw = it[THEME_KEY]
            if (raw == null) Theme.SYSTEM
            else {
                try {
                    Theme.valueOf(raw)
                } catch (e: IllegalArgumentException) {
                    Theme.SYSTEM
                }
            }
        }
    }

    override suspend fun setCurrentTheme(theme: Theme) {
        context.store.edit {
            it[THEME_KEY] = theme.name
        }
    }

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
    }

}
