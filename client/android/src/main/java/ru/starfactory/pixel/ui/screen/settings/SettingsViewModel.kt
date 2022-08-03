package ru.starfactory.pixel.ui.screen.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel : ViewModel() {
    val state = MutableStateFlow(SettingsViewState(SettingsViewState.Theme.SYSTEM))
}