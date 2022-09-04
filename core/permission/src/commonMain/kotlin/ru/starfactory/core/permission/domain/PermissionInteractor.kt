package ru.starfactory.core.permission.domain

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import ru.starfactory.core.permission.service.Permission
import ru.starfactory.core.permission.service.PermissionService

interface PermissionInteractor {
    fun getIsPermissionGranted(permission: Permission): Boolean
    fun observeIsPermissionGranted(permission: Permission): Flow<Boolean>
    suspend fun requestPermission(permission: Permission): Boolean
}

internal class PermissionInteractorImpl(private val permissionService: PermissionService) : PermissionInteractor {
    private val permissionChanged = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        .apply { tryEmit(Unit) }

    override fun getIsPermissionGranted(permission: Permission): Boolean = permissionService.getIsPermissionGranted(permission)

    override fun observeIsPermissionGranted(permission: Permission): Flow<Boolean> {
        return permissionChanged
            .map { getIsPermissionGranted(permission) }
            .distinctUntilChanged()
    }

    override suspend fun requestPermission(permission: Permission): Boolean {
        val isGranted = permissionService.requestPermission(permission)
        permissionChanged.emit(Unit)
        return isGranted
    }
}
