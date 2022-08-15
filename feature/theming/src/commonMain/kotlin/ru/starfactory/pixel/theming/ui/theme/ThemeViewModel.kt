package ru.starfactory.pixel.theming.ui.theme

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.theming.domain.ThemeInteractor

internal class ThemeViewModel(
    themeInteractor: ThemeInteractor
) : ViewModel() {
    val state = themeInteractor.observeCurrentTheme()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
}