package ru.starfactory.pixel.service.usb

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import ru.starfactory.core.utils.observeBroadcastIntents
import ru.starfactory.core.utils.shareDefault
import ru.starfactory.pixel.Tag

interface UsbService {
    fun observeUsbDevices(): Flow<Map<String, UsbDevice>>
    fun getUsbDevices(): Map<String, UsbDevice>
    fun findUsbDeviceByName(name: String): UsbDevice?
    suspend fun requestPermission(device: UsbDevice): Boolean

    fun getRawManager(): UsbManager
}

class UsbServiceImpl(
    private val context: Context,
    private val scope: CoroutineScope
) : UsbService {
    private val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager

    private val usbDevicesObservable = run {
        val filter = IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
            addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        }

        context.observeBroadcastIntents(filter, false)
            .map { getUsbDevices() }
            .onStart { emit(getUsbDevices()) }
            .onStart { Log.i(TAG, "Start observing usb devices") }
            .onCompletion { Log.i(TAG, "Stop observing usb devices") }
            .onEach { Log.i(TAG, "Usb devices: ${it.keys}") }
            .shareDefault(scope)
    }

    override fun observeUsbDevices(): Flow<Map<String, UsbDevice>> = usbDevicesObservable

    override fun getUsbDevices(): Map<String, UsbDevice> = usbManager.deviceList

    override fun findUsbDeviceByName(name: String): UsbDevice? = getUsbDevices()[name]

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

            deferredResponse.await()

            // This officially recommended by google way doesn't work
            // response.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)

            // This way works perfectly
            usbManager.hasPermission(device)
        }
    }

    override fun getRawManager(): UsbManager = usbManager

    companion object {
        private const val TAG = Tag.USB
        private const val ACTION_USB_PERMISSION = "com.starfactory.pixel.USB_PERMISSION"
    }
}