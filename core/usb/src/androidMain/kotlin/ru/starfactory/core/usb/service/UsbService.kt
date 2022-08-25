package ru.starfactory.core.usb.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice as UsbDeviceAndroid
import android.hardware.usb.UsbManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import ru.starfactory.core.coroutines.observeBroadcastIntents
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.logger.Log
import ru.starfactory.core.usb.domain.UsbDevice

internal interface UsbServiceAndroid : UsbService {
    fun getRawManager(): UsbManager
}

internal class UsbServiceAndroidImpl(
    private val context: Context,
    private val scope: CoroutineScope
) : UsbServiceAndroid {
    private val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager

    private val androidUsbDevicesObservable: SharedFlow<Map<String, UsbDeviceAndroid>> = run {
        val filter = IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
            addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        }

        context.observeBroadcastIntents(filter, false)
            .map { getUsbDevicesAndroid() }
            .onStart { emit(getUsbDevicesAndroid()) }
            .onStart { Log.i(TAG, "Start observing usb devices") }
            .onCompletion { Log.i(TAG, "Stop observing usb devices") }
            .onEach { Log.i(TAG, "Usb devices: ${it.keys}") }
            .shareDefault(scope)
    }

    private val usbDevicesObservable: SharedFlow<Map<String, UsbDevice>> =
        androidUsbDevicesObservable.map { it.toUsbDevice() }.shareDefault(scope)

    override fun observeUsbDevices(): Flow<Map<String, UsbDevice>> = usbDevicesObservable

    private fun getUsbDevicesAndroid(): Map<String, UsbDeviceAndroid> = usbManager.deviceList
    override fun getUsbDevices(): Map<String, UsbDevice> = getUsbDevicesAndroid().toUsbDevice()

    private fun findUsbDeviceAndroidByName(name: String): UsbDeviceAndroid? =
        getUsbDevicesAndroid()[name]

    override fun findUsbDeviceByName(name: String): UsbDevice? =
        findUsbDeviceAndroidByName(name)?.toUsbDevice()

    override suspend fun requestPermission(device: UsbDevice): Boolean {
        val androidDevice = findUsbDeviceAndroidByName(device.id) ?: return false
        if (usbManager.hasPermission(androidDevice)) return true

        return coroutineScope {
            val requestPermissionIntent =
                PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(ACTION_USB_PERMISSION),
                    PendingIntent.FLAG_IMMUTABLE
                )
            val filter = IntentFilter(ACTION_USB_PERMISSION)

            val deferredResponse = async {
                context.observeBroadcastIntents(filter, false)
                    .first()
            }

            usbManager.requestPermission(androidDevice, requestPermissionIntent)

            deferredResponse.await()

            // This officially recommended by google way doesn't work
            // response.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)

            // This way works perfectly
            usbManager.hasPermission(androidDevice)
        }
    }

    override fun getRawManager(): UsbManager = usbManager

    companion object {
        private const val TAG = "USB"
        private const val ACTION_USB_PERMISSION = "com.starfactory.pixel.USB_PERMISSION"
    }
}

private fun Map<String, UsbDeviceAndroid>.toUsbDevice(): Map<String, UsbDevice> =
    mapValues { it.value.toUsbDevice() }

private fun UsbDeviceAndroid.toUsbDevice(): UsbDevice = UsbDevice(this.deviceName)