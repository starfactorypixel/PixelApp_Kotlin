package ru.starfactory.core.logger

import java.util.concurrent.CopyOnWriteArrayList

object LogConfig {
    private val loggers_ = CopyOnWriteArrayList<Logger>()

    val loggers: List<Logger> = loggers_

    fun addLogger(logger: Logger) {
        loggers_.add(logger)
    }
}

expect fun LogConfig.setupDefault()
