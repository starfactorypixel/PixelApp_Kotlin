package ru.starfactory.core.logger

@Suppress("MemberNameEqualsClassName")
object Log {

    fun i(tag: String, message: String) = log(LogLevel.INFO, tag) { message }
    fun i(tag: String, message: () -> String) = log(LogLevel.INFO, tag, message)

    fun d(tag: String, message: String) = log(LogLevel.DEBUG, tag) { message }
    fun d(tag: String, message: () -> String) = log(LogLevel.DEBUG, tag, message)

    fun t(tag: String, message: String) = log(LogLevel.TRACE, tag) { message }
    fun t(tag: String, message: () -> String) = log(LogLevel.TRACE, tag, message)

    fun log(level: LogLevel, tag: String, message: () -> String) {
        LogConfig.loggers.forEach { it.log(level, tag, message) }
    }

    fun log(level: LogLevel, tag: String, message: () -> String, e: Throwable) {
        LogConfig.loggers.forEach { it.log(level, tag, message, e) }
    }
}
