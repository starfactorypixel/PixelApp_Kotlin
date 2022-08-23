package ru.starfactory.core.logger

interface Logger {
    fun log(level: LogLevel, tag: String, message: () -> String)
    fun log(level: LogLevel, tag: String, message: () -> String, e: Throwable)
}