package ru.starfactory.pixel.service.usb

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.starfactory.core.utils.observeBroadcastIntents
import ru.starfactory.pixel.Tag

interface UsbService {
    fun observeUsbDevices(): Flow<Map<String, UsbDevice>>
    fun getUsbDevices(): Map<String, UsbDevice>
    suspend fun requestPermission(device: UsbDevice): Boolean
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

    override suspend fun requestPermission(device: UsbDevice): Boolean {
        if (usbManager.hasPermission(device)) return true

        return coroutineScope {
            val requestPermissionIntent =
                PendingIntent.getBroadcast(context, 0, Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_IMMUTABLE)
            val filter = IntentFilter(ACTION_USB_PERMISSION)

            val deferredResponse = async {
                context.observeBroadcastIntents(filter, false)
                    .first()
            }

            usbManager.requestPermission(device, requestPermissionIntent)

            val response = deferredResponse.await()

            response.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
        }
    }

    companion object {
        private const val TAG = Tag.USB
        private const val ACTION_USB_PERMISSION = "com.starfactory.pixel.USB_PERMISSION"
    }
}