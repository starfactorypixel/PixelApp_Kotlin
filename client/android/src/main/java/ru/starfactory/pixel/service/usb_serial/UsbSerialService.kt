package ru.starfactory.pixel.service.usb_serial

import android.hardware.usb.UsbDevice
import android.util.Log
import com.hoho.android.usbserial.driver.CdcAcmSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.pixel.Tag
import ru.starfactory.pixel.service.usb.UsbService
import java.io.IOException

interface UsbSerialService {
    fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>>
    suspend fun getUsbSerialDevices(): Map<String, UsbSerialDevice>
    suspend fun findUsbSerialDeviceByName(deviceName: String): UsbSerialDevice?
    suspend fun connect(deviceName: String, block: suspend CoroutineScope.(connection: UsbSerialConnection) -> Unit)

    interface UsbSerialConnection {
        suspend fun send(byteArray: ByteArray)
        suspend fun receive(byteArray: ByteArray, timeout: Int): Int
    }
}

class UsbSerialServiceImpl(
    private val usbService: UsbService,
    private val scope: CoroutineScope,
) : UsbSerialService {
    private val usbManager = usbService.getRawManager()

    private val probeTable = UsbSerialProber.getDefaultProbeTable().apply {
        addProduct(0x2A03, 0x43, CdcAcmSerialDriver::class.java) // Arduino Uno
    }

    private val prober = UsbSerialProber(probeTable)

    private val usbSerialDevicesObservable: Flow<Map<String, UsbSerialDevice>> =
        usbService.observeUsbDevices()
            .map { devices -> devices.toUsbSerialDevices() }
            .shareDefault(scope)

    override fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>> = usbSerialDevicesObservable

    override suspend fun getUsbSerialDevices(): Map<String, UsbSerialDevice> = usbManager.deviceList.toUsbSerialDevices()

    override suspend fun findUsbSerialDeviceByName(deviceName: String): UsbSerialDevice? = getUsbSerialDevices()[deviceName]

    override suspend fun connect(
        deviceName: String,
        block: suspend CoroutineScope.(connection: UsbSerialService.UsbSerialConnection) -> Unit
    ) =
        // TODO это конечно плохая реализация, это так чисто для проверки
        withContext(Dispatchers.IO) {
            val usbSerialDevice = findUsbSerialDeviceByName(deviceName) ?: throw IOException("Device $deviceName not found")
            val driver = usbSerialDevice.driver ?: throw IllegalStateException("Device $deviceName, haven't driver")

            val deviceConnection = usbManager.openDevice(usbSerialDevice.device)

            val port = driver.ports[0]
            try {
                port.open(deviceConnection)
                port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)

                val connection = object : UsbSerialService.UsbSerialConnection {
                    override suspend fun send(byteArray: ByteArray) {
                        port.write(byteArray, Int.MAX_VALUE)
                    }

                    override suspend fun receive(byteArray: ByteArray, timeout: Int): Int {
                        return port.read(byteArray, timeout)
                    }
                }

                coroutineScope {
                    block(this, connection)
                }
            } finally {
                withContext(NonCancellable) {
                    try {
                        port.close()
                    } catch (e: IOException) {
                        // no action
                    }
                }
            }
        }

    private fun Map<String, UsbDevice>.toUsbSerialDevices() = mapValues { UsbSerialDevice(it.value, prober.probeDevice(it.value)) }

    fun test() {
        GlobalScope.launch {
            observeUsbSerialDevices().collectLatest {
                Log.i(TAG, "Devices: $it")
            }
//            val probeTable = ProbeTable().apply {
//                addProduct(0x2a03, 0x43, CdcAcmSerialDriver::class.java)
//            }
//
//            // Find all available drivers from attached devices.
//            val availableDrivers = UsbSerialProber(probeTable).findAllDrivers(usbManager)
//            if (availableDrivers.isEmpty()) {
//                return@launch
//            }
//
//
//            // Open a connection to the first available driver.
//            val driver = availableDrivers[0]
//            usbService.requestPermission(driver.device)
//            val connection = usbManager.openDevice(driver.device)
//                ?: // add UsbManager.requestPermission(driver.getDevice(), ..) handling here
//                return@launch
//
//            val port = driver.ports[0] // Most devices have just one port (port 0)
//
//            port.open(connection)
//            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
//
//
//            val buffer = ByteArray(1024)
//            while (true) {
//                val readed = port.read(buffer, 500)
//                Log.i(TAG, String(buffer, offset = 0, length = readed))
//            }
        }
    }

    companion object {
        private const val TAG = Tag.USB_SERIAL
    }

}