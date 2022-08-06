package ru.starfactory.pixel.ui.screen.debug.usb_serial

sealed class UsbSerialViewState {
    object Loading : UsbSerialViewState()
    object NoDevices : UsbSerialViewState()
    data class HasDevices(val devices: List<UsbSerialDeviceState>) : UsbSerialViewState()


    data class UsbSerialDeviceState(
        val name: String,
        val vendorId: Int,
        val vendorName: String,
        val productId: Int,
        val productName: String,
        val driverState: UsbSerialDeviceDriverState
    )

    sealed class UsbSerialDeviceDriverState {
        object NoDriver : UsbSerialDeviceDriverState()
        data class HasDriver(val name: String) : UsbSerialDeviceDriverState()
    }
}
