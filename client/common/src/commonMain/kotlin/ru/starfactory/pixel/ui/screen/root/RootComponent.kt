package ru.starfactory.pixel.ui.screen.root

import com.arkivanov.decompose.ComponentContext
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.subDI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.core.navigation.ui.defaultChildStack
import ru.starfactory.core.permission.ui.PermissionViewModel
import ru.starfactory.pixel.main_screen.ui.screen.MainScreen
import ru.starfactory.pixel.theming.ui.theme.ThemeViewModel

class RootComponent(di: DI, componentContext: ComponentContext) {
    val rootViewModel: RootViewModel = decomposeViewModel(di, componentContext.instanceKeeper)
    val permissionViewModel: PermissionViewModel = decomposeViewModel(di, componentContext.instanceKeeper)
    val themeViewModel: ThemeViewModel = decomposeViewModel(di, componentContext.instanceKeeper)

    private val navigation = rootViewModel.navigation

    private val subDi = subDI(di) {
        bindSingleton(tag = NavigationType.ROOT) { navigation }
    }

    val childStack = componentContext.defaultChildStack(navigation, MainScreen, subDi, "root-navigation")
}