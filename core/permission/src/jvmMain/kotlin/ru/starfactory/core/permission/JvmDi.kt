package ru.starfactory.core.permission

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.permission.serivce.JvmPermissionService
import ru.starfactory.core.permission.service.PermissionService

internal actual fun Modules.corePermissionPlatform() = DI.Module("core-permission-platform") {
    bindSingleton<PermissionService> { JvmPermissionService() }
}