package ru.starfactory.pixel

import org.kodein.di.DI
import ru.starfactory.core.logger.LogConfig
import ru.starfactory.core.logger.setupDefault

fun initApp(di: DI) {
    LogConfig.setupDefault()
}
