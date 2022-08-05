package ru.starfactory.pixel.ui.screen.debug.usb

sealed class UsbViewState {
    object Loading : UsbViewState()
    object NoDevices : UsbViewState()
    data class HasDevices(val devices: List<UsbDeviceState>) : UsbViewState()


    data class UsbDeviceState(
        val name: String,
        val vendorId: Int,
        val vendorName: String,
        val productId: Int,
        val productName: String
    )
}
