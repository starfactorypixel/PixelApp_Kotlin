package ru.starfactory.pixel.service.permission

import android.content.Context
import android.content.pm.PackageManager

interface PermissionService {
    suspend fun requestPermission(permission: Permission): Boolean
}

class PermissionServiceImpl(private val context: Context) : PermissionService {
    var permissionRequester: PermissionRequester? = null

    override suspend fun requestPermission(permission: Permission): Boolean {
        if (context.checkSelfPermission(permission.raw) == PackageManager.PERMISSION_GRANTED) return true

        return permissionRequester?.requestPermission(permission = permission.raw)
            ?: throw RuntimeException("Permission requester not attached")
    }
}