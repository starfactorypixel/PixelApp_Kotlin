package ru.starfactory.core.usb.domain

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.usb.service.UsbService

interface UsbInteractor {
    fun observeUsbDevices(): Flow<Map<String, UsbDevice>>
    fun getUsbDevices(): Map<String, UsbDevice>
    fun findUsbDeviceByName(name: String): UsbDevice?
    suspend fun requestPermission(device: UsbDevice): Boolean
}

internal class UsbInteractorImpl(
    private val usbService: UsbService,
) : UsbInteractor {
    override fun observeUsbDevices(): Flow<Map<String, UsbDevice>> = usbService.observeUsbDevices()

    override fun getUsbDevices(): Map<String, UsbDevice> = usbService.getUsbDevices()

    override fun findUsbDeviceByName(name: String): UsbDevice? = usbService.findUsbDeviceByName(name)

    override suspend fun requestPermission(device: UsbDevice): Boolean = usbService.requestPermission(device)
}
