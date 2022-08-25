package ru.starfactory.core.usb.service

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.usb.domain.UsbDevice

interface UsbService {
    fun observeUsbDevices(): Flow<Map<String, UsbDevice>>
    fun getUsbDevices(): Map<String, UsbDevice>
    fun findUsbDeviceByName(name: String): UsbDevice?
    suspend fun requestPermission(device: UsbDevice): Boolean
}