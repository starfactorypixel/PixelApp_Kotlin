package ru.starfactory.core.permission.service

internal interface PermissionService {
    var permissionRequester: PermissionRequester?

    fun getIsPermissionGranted(permission: Permission): Boolean
    suspend fun requestPermission(permission: Permission): Boolean
}