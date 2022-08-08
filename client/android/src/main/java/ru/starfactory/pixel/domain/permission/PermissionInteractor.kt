package ru.starfactory.pixel.domain.permission

import ru.starfactory.pixel.service.permission.Permission
import ru.starfactory.pixel.service.permission.PermissionService

interface PermissionInteractor {
    suspend fun requestPermission(permission: Permission): Boolean
}

class PermissionInteractorImpl(private val permissionService: PermissionService) : PermissionInteractor {
    override suspend fun requestPermission(permission: Permission): Boolean = permissionService.requestPermission(permission)
}