package ru.starfactory.pixel.service.permission

import android.content.Context
import android.content.pm.PackageManager

interface PermissionService {
    fun getIsPermissionGranted(permission: Permission): Boolean
    suspend fun requestPermission(permission: Permission): Boolean
}

class PermissionServiceImpl(private val context: Context) : PermissionService {
    var permissionRequester: PermissionRequester? = null

    override fun getIsPermissionGranted(permission: Permission): Boolean {
        return context.checkSelfPermission(permission.raw) == PackageManager.PERMISSION_GRANTED
    }

    override suspend fun requestPermission(permission: Permission): Boolean {
        if (getIsPermissionGranted(permission)) return true

        return permissionRequester?.requestPermission(permission = permission.raw)
            ?: throw RuntimeException("Permission requester not attached")
    }
}