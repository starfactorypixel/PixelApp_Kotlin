package ru.starfactory.pixel.dashboard_screen

import org.kodein.di.DI
import org.kodein.di.bindProvider
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard.DashboardViewModel

fun Modules.featureDashboardScreen() = DI.Module("feature-dashboard-screen") {
    bindProvider { DashboardViewModel(i(), i()) }
}