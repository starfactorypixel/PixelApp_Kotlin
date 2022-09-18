package ru.starfactory.pixel.keep_screen_on.ui

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.keep_screen_on.domain.KeepScreenOnInteractor

class KeepScreenOnViewModel(
    keepScreenOnInteractor: KeepScreenOnInteractor,
) : ViewModel() {
    val state = keepScreenOnInteractor.observeIsScreenAlwaysOn().stateIn(viewModelScope, SharingStarted.Eagerly, false)
}
