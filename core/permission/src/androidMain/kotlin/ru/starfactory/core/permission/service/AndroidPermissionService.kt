package ru.starfactory.core.permission.service

import android.content.Context
import android.content.pm.PackageManager

internal class AndroidPermissionService(private val context: Context) : PermissionService {
    override var permissionRequester: PermissionRequester? = null

    override fun getIsPermissionGranted(permission: Permission): Boolean {
        return context.checkSelfPermission(permission.raw) == PackageManager.PERMISSION_GRANTED
    }

    override suspend fun requestPermission(permission: Permission): Boolean {
        if (getIsPermissionGranted(permission)) return true

        return permissionRequester?.requestPermission(permission = permission.raw)
            ?: throw RuntimeException("Permission requester not attached")
    }
}

val Permission.raw: String
    get() = when (this) {
        Permission.BLUETOOTH_CONNECT -> "android.permission.BLUETOOTH_CONNECT"
    }