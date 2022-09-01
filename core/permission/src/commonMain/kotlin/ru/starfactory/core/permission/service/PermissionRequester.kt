package ru.starfactory.core.permission.service

internal interface PermissionRequester {
    suspend fun requestPermission(permission: String): Boolean
}