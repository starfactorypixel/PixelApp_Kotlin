package ru.starfactory.pixel.ui.screen.settings

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.theming.domain.Theme
import ru.starfactory.pixel.theming.domain.ThemeInteractor

class SettingsViewModel(
    private val themeInteractor: ThemeInteractor,
) : ViewModel() {
    val state = themeInteractor.observeCurrentTheme().map { SettingsViewState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SettingsViewState(Theme.SYSTEM))

    fun onChangeTheme(theme: Theme) {
        viewModelScope.launch {
            themeInteractor.setCurrentTheme(theme)
        }
    }
}