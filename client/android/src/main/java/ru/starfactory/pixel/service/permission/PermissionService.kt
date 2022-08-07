package ru.starfactory.pixel.service.permission

import android.content.Context
import android.content.pm.PackageManager

interface PermissionService {
    suspend fun requestPermission(permission: String): Boolean
}

class PermissionServiceImpl(private val context: Context) : PermissionService {
    var permissionRequester: PermissionRequester? = null

    override suspend fun requestPermission(permission: String): Boolean {
        if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) return true

        return permissionRequester?.requestPermission(permission = permission)
            ?: throw RuntimeException("Permission requester not attached")
    }
}