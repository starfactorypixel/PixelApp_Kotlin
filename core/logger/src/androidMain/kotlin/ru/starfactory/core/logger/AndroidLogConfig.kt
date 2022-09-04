package ru.starfactory.core.logger

import android.util.Log

actual fun LogConfig.setupDefault() {
    addLogger(LogcatLogger())
}

private class LogcatLogger : Logger {
    override fun log(level: LogLevel, tag: String, message: () -> String) {
        Log.println(level.toAndroidLogLevel(), tag, message())
    }

    override fun log(level: LogLevel, tag: String, message: () -> String, e: Throwable) {
        Log.println(level.toAndroidLogLevel(), tag, message() + '\n' + Log.getStackTraceString(e))
    }

    fun LogLevel.toAndroidLogLevel() = when (this) {
        LogLevel.TRACE -> Log.VERBOSE
        LogLevel.DEBUG -> Log.DEBUG
        LogLevel.INFO -> Log.INFO
        LogLevel.WARN -> Log.WARN
        LogLevel.ERROR -> Log.ERROR
        LogLevel.FATAL -> Log.ASSERT
    }
}
