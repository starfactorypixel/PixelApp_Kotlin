package ru.starfactory.pixel

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindSingleton

class App : Application(), DIAware {
    private val applicationScope = CoroutineScope(SupervisorJob())
    override val di: DI by DI.lazy {
        bindSingleton { applicationScope }
        importOnce(MainModule())
    }
}