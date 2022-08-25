package ru.starfactory.pixel.domain.usb_serial

//
//interface UsbSerialInteractor {
//    fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>>
//    suspend fun connect(deviceName: String, block: suspend CoroutineScope.(connection: UsbSerialService.UsbSerialConnection) -> Unit)
//}
//
//class UsbSerialInteractorImpl(
//    private val usbSerialService: UsbSerialService
//) : UsbSerialInteractor {
//    override fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>> = usbSerialService.observeUsbSerialDevices()
//    override suspend fun connect(deviceName: String, block: suspend CoroutineScope.(connection: UsbSerialService.UsbSerialConnection) -> Unit) =
//        usbSerialService.connect(deviceName, block)
//}
