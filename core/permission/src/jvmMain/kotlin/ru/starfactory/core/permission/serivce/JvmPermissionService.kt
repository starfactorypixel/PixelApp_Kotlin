package ru.starfactory.core.permission.serivce

import ru.starfactory.core.permission.service.Permission
import ru.starfactory.core.permission.service.PermissionRequester
import ru.starfactory.core.permission.service.PermissionService

internal class JvmPermissionService : PermissionService {
    override var permissionRequester: PermissionRequester? = null

    override fun getIsPermissionGranted(permission: Permission): Boolean {
        return true
    }

    override suspend fun requestPermission(permission: Permission): Boolean {
        return true
    }

}
