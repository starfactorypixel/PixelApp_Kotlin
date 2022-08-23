package ru.starfactory.core.logger

import java.io.PrintWriter
import java.io.StringWriter
import java.net.UnknownHostException

actual fun LogConfig.setupDefault() {
    addLogger(JvmLogger())
}

// TODO Sumin добавить нормальный логер
private class JvmLogger : Logger {
    override fun log(level: LogLevel, tag: String, message: () -> String) {
        println("[$level][$tag] ${message()}")
    }

    override fun log(level: LogLevel, tag: String, message: () -> String, e: Throwable) {
        println("[$level][$tag] ${message()}\n${getStackTraceString(e)}")
    }

    fun getStackTraceString(tr: Throwable?): String {
        if (tr == null) {
            return ""
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        var t = tr
        while (t != null) {
            if (t is UnknownHostException) {
                return ""
            }
            t = t.cause
        }
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}