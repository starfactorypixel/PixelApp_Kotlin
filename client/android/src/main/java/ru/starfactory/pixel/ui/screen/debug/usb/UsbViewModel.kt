package ru.starfactory.pixel.ui.screen.debug.usb

import android.hardware.usb.UsbDevice
import kotlinx.coroutines.flow.*
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.usb.UsbInteractor

class UsbViewModel(
    private val usbInteractor: UsbInteractor,
) : ViewModel() {
    val state: StateFlow<UsbViewState> = usbInteractor.observeUsbDevices()
        .map { if (it.isEmpty()) UsbViewState.NoDevices else UsbViewState.HasDevices(getDeviceList(it)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, UsbViewState.Loading)

    private fun getDeviceList(devices: Map<String, UsbDevice>): List<UsbViewState.UsbDeviceState> {
        return devices.values.map { it.toViewState() }.sortedBy { it.name }
    }

    private fun UsbDevice.toViewState(): UsbViewState.UsbDeviceState =
        UsbViewState.UsbDeviceState(
            name = deviceName,
            vendorId = vendorId,
            vendorName = manufacturerName ?: "<no name>",
            productId = productId,
            productName = productName ?: "<no name>"
        )
}