package ru.starfactory.pixel.ui.screen.request_permission

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.permission.PermissionInteractor
import ru.starfactory.pixel.service.permission.Permission

class RequestPermissionViewModel(
    private val permissionInteractor: PermissionInteractor,
    private val permission: Permission,
) : ViewModel() {
    val navigateNext = Channel<Unit>()

    fun onClickRequestPermission() {
        viewModelScope.launch {
            val result = permissionInteractor.requestPermission(permission = permission)
            if (result) navigateNext.send(Unit)
        }
    }
}