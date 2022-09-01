package ru.starfactory.core.permission

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.permission.domain.PermissionInteractor
import ru.starfactory.core.permission.domain.PermissionInteractorImpl
import ru.starfactory.core.permission.ui.PermissionViewModel

fun Modules.corePermission() = DI.Module("core-permission") {
    importOnce(Modules.corePermissionPlatform())
    bindSingleton<PermissionInteractor> { PermissionInteractorImpl(i()) }
    bindProvider { PermissionViewModel(i()) }
}

internal expect fun Modules.corePermissionPlatform(): DI.Module