package ru.starfactory.pixel.ui.screen.debug.usb_serial

import android.util.Log
import com.hoho.android.usbserial.driver.UsbSerialDriver
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.startfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.Tag
import ru.starfactory.pixel.domain.usb.UsbInteractor
import ru.starfactory.pixel.domain.usb_serial.UsbSerialInteractor
import ru.starfactory.pixel.service.usb_serial.UsbSerialDevice

class UsbSerialViewModel(
    private val usbInteractor: UsbInteractor,
    private val usbSerialInteractor: UsbSerialInteractor,
) : ViewModel() {
    val state: StateFlow<UsbSerialViewState> = usbSerialInteractor.observeUsbSerialDevices()
        .map { if (it.isEmpty()) UsbSerialViewState.NoDevices else UsbSerialViewState.HasDevices(getDeviceList(it)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, UsbSerialViewState.Loading)

    val navigateToSelectUsbParamsScreen = Channel<String>() // param is device name

    private fun getDeviceList(devices: Map<String, UsbSerialDevice>): List<UsbSerialViewState.UsbSerialDeviceState> {
        return devices.values.map { it.toViewState() }.sortedBy { it.name }
    }

    fun onClickDevice(deviceName: String) {
        viewModelScope.launch {
            val device = usbInteractor.findUsbDeviceByName(deviceName)
            if (device == null) {
                Log.e(Tag.USB_SERIAL, "Selected usb device not found")
                // TODO Sumin: send non fatal crash
                return@launch
            }
            if (!usbInteractor.requestPermission(device)) {
                return@launch
            }

            navigateToSelectUsbParamsScreen.send(deviceName)
        }
    }

    private fun UsbSerialDevice.toViewState(): UsbSerialViewState.UsbSerialDeviceState =
        UsbSerialViewState.UsbSerialDeviceState(
            name = device.deviceName,
            vendorId = device.vendorId,
            vendorName = device.manufacturerName ?: "<no name>",
            productId = device.productId,
            productName = device.productName ?: "<no name>",
            driverState = driver.toViewState()
        )

    private fun UsbSerialDriver?.toViewState(): UsbSerialViewState.UsbSerialDeviceDriverState {
        return if (this == null) UsbSerialViewState.UsbSerialDeviceDriverState.NoDriver
        else UsbSerialViewState.UsbSerialDeviceDriverState.HasDriver(this.javaClass.simpleName)
    }
}