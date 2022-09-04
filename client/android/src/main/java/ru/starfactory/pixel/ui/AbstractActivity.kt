package ru.starfactory.pixel.ui

import androidx.activity.ComponentActivity
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

abstract class AbstractActivity : ComponentActivity(), DIAware {
    override val di: DI by closestDI()
}
