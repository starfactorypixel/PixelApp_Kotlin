package ru.starfactory.pixel.service.usb

import android.content.Context
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.starfactory.core.utils.observeBroadcastIntents
import ru.starfactory.pixel.Tag

interface UsbService {
    fun observeUsbDevices(): Flow<Map<String, UsbDevice>>
    fun getUsbDevices(): Map<String, UsbDevice>
}

class UsbServiceImpl(private val context: Context) : UsbService {
    private val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager

    override fun observeUsbDevices(): Flow<Map<String, UsbDevice>> {
        val filter = IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
            addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        }

        return context.observeBroadcastIntents(filter, false)
            .map { getUsbDevices() }
            .onStart { emit(getUsbDevices()) }
    }

    override fun getUsbDevices(): Map<String, UsbDevice> {
        return usbManager.deviceList
    }

    companion object {
        private const val TAG = Tag.USB
    }
}