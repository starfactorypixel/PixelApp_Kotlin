package ru.starfactory.pixel.service.usb_serial

import android.util.Log
import com.hoho.android.usbserial.driver.CdcAcmSerialDriver
import com.hoho.android.usbserial.driver.ProbeTable
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.starfactory.pixel.Tag
import ru.starfactory.pixel.service.usb.UsbService

interface UsbSerialService {
}

class UsbSerialServiceImpl(private val usbService: UsbService) : UsbSerialService {
    private val usbManager = usbService.getRawManager()
    fun test() {
        GlobalScope.launch {

            val probeTable = ProbeTable().apply {
                addProduct(0x2a03, 0x43, CdcAcmSerialDriver::class.java)
            }

            // Find all available drivers from attached devices.
            val availableDrivers = UsbSerialProber(probeTable).findAllDrivers(usbManager)
            if (availableDrivers.isEmpty()) {
                return@launch
            }


            // Open a connection to the first available driver.
            val driver = availableDrivers[0]
            usbService.requestPermission(driver.device)
            val connection = usbManager.openDevice(driver.device)
                ?: // add UsbManager.requestPermission(driver.getDevice(), ..) handling here
                return@launch

            val port = driver.ports[0] // Most devices have just one port (port 0)

            port.open(connection)
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)


            val buffer = ByteArray(1024)
            while (true) {
                val readed = port.read(buffer, 500)
                Log.i(TAG, String(buffer, offset = 0, length = readed))
            }
        }
    }

    companion object {
        private const val TAG = Tag.USB_SERIAL
    }

}