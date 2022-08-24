package ru.starfactory.core.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelable
import org.kodein.di.DI
import ru.starfactory.core.navigation.ui.ScreenInstance

interface Screen : Parcelable {
    fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance
}
