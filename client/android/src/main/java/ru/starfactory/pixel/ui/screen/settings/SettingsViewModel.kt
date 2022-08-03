package ru.starfactory.pixel.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    val state = MutableStateFlow(SettingsViewState(SettingsViewState.Theme.SYSTEM))

    fun onChangeTheme(theme: SettingsViewState.Theme) {
        state.update {
            it.copy(theme = theme)
        }
    }
}