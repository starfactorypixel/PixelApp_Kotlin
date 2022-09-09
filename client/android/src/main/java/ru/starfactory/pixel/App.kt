package ru.starfactory.pixel

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules

class App : Application(), DIAware {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    override val di: DI by DI.lazy {
        bindSingleton<Context> { this@App }
        bindSingleton { applicationScope }
        importOnce(Modules.mainCommonModule())
    }

    override fun onCreate() {
        super.onCreate()
        initApp(di)
    }
}
