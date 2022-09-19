package ru.starfactory.core.compose

import androidx.compose.ui.unit.LayoutDirection

fun LayoutDirection.reverse(): LayoutDirection = when (this) {
    LayoutDirection.Ltr -> LayoutDirection.Rtl
    LayoutDirection.Rtl -> LayoutDirection.Ltr
}
