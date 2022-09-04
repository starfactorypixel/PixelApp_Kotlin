package ru.starfactory.core.serial.usb.service

import com.hoho.android.usbserial.driver.CdcAcmSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialProber
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.serial.domain.SerialDeviceId
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.usb.domian.UsbSerialDevice
import ru.starfactory.core.usb.service.UsbServiceAndroid
import android.hardware.usb.UsbDevice as UsbDeviceAndroid

internal interface UsbSerialServiceAndroid : UsbSerialService {
//    fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDeviceAndroid>>
//    suspend fun getUsbSerialDevices(): Map<String, UsbSerialDeviceAndroid>
//    suspend fun findUsbSerialDeviceByName(deviceName: String): UsbSerialDeviceAndroid?
//    suspend fun connect(deviceName: String, block: suspend CoroutineScope.(connection: UsbSerialConnection) -> Unit)
//
// //    interface UsbSerialConnection {
// //        suspend fun send(byteArray: ByteArray)
// //        suspend fun receive(byteArray: ByteArray, timeout: Int): Int
// //    }
}

internal class UsbSerialServiceAndroidImpl(
    usbService: UsbServiceAndroid,
    private val scope: CoroutineScope,
) : UsbSerialServiceAndroid {
    private val usbManager = usbService.getRawManager()

    private val probeTable = UsbSerialProber.getDefaultProbeTable().apply {
        addProduct(0x2A03, 0x43, CdcAcmSerialDriver::class.java) // Arduino Uno
    }

    private val prober = UsbSerialProber(probeTable)

    private val usbSerialDevicesObservable: Flow<List<UsbSerialDevice>> =
        usbService.observeUsbDevicesAndroid()
            .map { devices -> devices.values }
            .map { devices -> devices.toSerialDevices() }
            .shareDefault(scope)

    override fun observeUsbSerialDevices(): Flow<List<UsbSerialDevice>> = usbSerialDevicesObservable

    //            .map { devices -> devices.toUsbSerialDevices() }
//            .shareDefault(scope)
//
//    override fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDeviceAndroid>> = usbSerialDevicesObservable
//
//    //
//    override suspend fun getUsbSerialDevices(): Map<String, UsbSerialDeviceAndroid> =
//        usbService.getUsbDevicesAndroid().toUsbSerialDevices()
//
//    override suspend fun findUsbSerialDeviceByName(deviceName: String): UsbSerialDeviceAndroid? =
//        getUsbSerialDevices()[deviceName]
//
//
//    override suspend fun connect(
//        deviceName: String,
//        block: suspend CoroutineScope.(connection: UsbSerialServiceAndroid.UsbSerialConnection) -> Unit
//    ) =
//        // TODO это конечно плохая реализация, это так чисто для проверки
//        withContext(Dispatchers.IO) {
//            val usbSerialDevice =
//                findUsbSerialDeviceByName(deviceName) ?: throw IOException("Device $deviceName not found")
//            val driver = usbSerialDevice.driver ?: throw IllegalStateException("Device $deviceName, haven't driver")
//
//            val deviceConnection = usbManager.openDevice(usbSerialDevice.deviceAndroid)
//
//            val port = driver.ports[0]
//            try {
//                port.open(deviceConnection)
//                port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
//
//                val connection = object : UsbSerialServiceAndroid.UsbSerialConnection {
//                    override suspend fun send(byteArray: ByteArray) {
//                        port.write(byteArray, Int.MAX_VALUE)
//                    }
//
//                    override suspend fun receive(byteArray: ByteArray, timeout: Int): Int {
//                        return port.read(byteArray, timeout)
//                    }
//                }
//
//                coroutineScope {
//                    block(this, connection)
//                }
//            } finally {
//                withContext(NonCancellable) {
//                    try {
//                        port.close()
//                    } catch (e: IOException) {
//                        // no action
//                    }
//                }
//            }
//        }
//
    private fun Collection<UsbDeviceAndroid>.toSerialDevices(): List<UsbSerialDevice> =
        mapNotNull {
            val driver = prober.probeDevice(it)
            if (driver != null) {
                UsbSerialDevice(
                    id = SerialDeviceId(SerialDeviceType.USB, it.deviceId.toString()),
                    type = SerialDeviceType.USB,
                    name = it.deviceName,
                )
            } else {
                null
            }
        }
//
// //    fun test() {
// //        GlobalScope.launch {
// //            observeUsbSerialDevices().collectLatest {
// //                Log.i(TAG, "Devices: $it")
// //            }
// //            val probeTable = ProbeTable().apply {
// //                addProduct(0x2a03, 0x43, CdcAcmSerialDriver::class.java)
// //            }
// //
// //            // Find all available drivers from attached devices.
// //            val availableDrivers = UsbSerialProber(probeTable).findAllDrivers(usbManager)
// //            if (availableDrivers.isEmpty()) {
// //                return@launch
// //            }
// //
// //
// //            // Open a connection to the first available driver.
// //            val driver = availableDrivers[0]
// //            usbService.requestPermission(driver.device)
// //            val connection = usbManager.openDevice(driver.device)
// //                ?: // add UsbManager.requestPermission(driver.getDevice(), ..) handling here
// //                return@launch
// //
// //            val port = driver.ports[0] // Most devices have just one port (port 0)
// //
// //            port.open(connection)
// //            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
// //
// //
// //            val buffer = ByteArray(1024)
// //            while (true) {
// //                val readed = port.read(buffer, 500)
// //                Log.i(TAG, String(buffer, offset = 0, length = readed))
// //            }
// //        }
// //    }
}
