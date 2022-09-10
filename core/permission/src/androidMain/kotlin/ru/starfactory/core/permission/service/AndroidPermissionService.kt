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
            ?: error("Permission requester not attached")
    }
}

val Permission.raw: String
    get() = when (this) {
        Permission.BLUETOOTH_CONNECT -> {
            // old android version use location permission to bluetooth access
            // https://developer.android.com/guide/topics/connectivity/bluetooth/permissions#declare-android11-or-lower
            if (android.os.Build.VERSION.SDK_INT > 30) "android.permission.BLUETOOTH_CONNECT"
            else "android.permission.ACCESS_FINE_LOCATION"
        }
    }
