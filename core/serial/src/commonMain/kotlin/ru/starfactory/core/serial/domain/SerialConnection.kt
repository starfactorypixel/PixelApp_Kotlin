package ru.starfactory.core.serial.domain

import java.io.InputStream
import java.io.OutputStream

interface SerialConnection {
    val inputStream: InputStream
    val outputStream: OutputStream
}
