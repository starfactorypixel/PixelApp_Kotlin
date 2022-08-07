package ru.starfactory.pixel.service.permission

interface PermissionRequester {
    suspend fun requestPermission(permission: String): Boolean
}