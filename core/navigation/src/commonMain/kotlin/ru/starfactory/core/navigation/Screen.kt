package ru.starfactory.core.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelable

interface Screen : Parcelable {
    @Composable
    fun ScreenView()
}
