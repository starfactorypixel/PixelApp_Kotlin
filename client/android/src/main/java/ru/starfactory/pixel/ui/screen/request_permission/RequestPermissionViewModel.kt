package ru.starfactory.pixel.ui.screen.request_permission

import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.permission.PermissionInteractor
import ru.starfactory.pixel.service.permission.Permission

class RequestPermissionViewModel(
    private val permissionInteractor: PermissionInteractor,
    private val permission: Permission,
) : ViewModel() {
}