package ru.starfactory.pixel.ui.screen.debug.usb_serial.terminal

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.usb_serial.UsbSerialInteractor

class UsbSerialTerminalViewModel(
    private val usbSerialInteractor: UsbSerialInteractor,
    private val usbDeviceName: String,
) : ViewModel() {
    val state = MutableStateFlow("")

    init {
        val sb = StringBuilder()
        val buffer = ByteArray(256)

        viewModelScope.launch {
            usbSerialInteractor.connect(deviceName = usbDeviceName) { connection ->
                while (true) {
                    val read = connection.receive(buffer, 200)
                    sb.append(String(buffer, 0, read))
                    state.value = sb.toString()
                }
            }
        }
    }
}