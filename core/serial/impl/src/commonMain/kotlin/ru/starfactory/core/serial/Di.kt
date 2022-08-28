package ru.starfactory.core.serial

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.core.serial.domain.SerialInteractorImpl
import ru.starfactory.core.serial.usb.coreSerialUsb

fun Modules.coreSerial() = DI.Module("core-serial") {
    importOnce(coreSerialUsb())

    bindSingleton<SerialInteractor> { SerialInteractorImpl(i()) }
}