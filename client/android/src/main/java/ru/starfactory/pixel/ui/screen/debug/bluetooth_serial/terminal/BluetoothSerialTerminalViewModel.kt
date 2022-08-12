package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial.terminal

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.startfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.bluetooth.BluetoothInteractor
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice
import java.util.*

class BluetoothSerialTerminalViewModel(
    private val bluetoothInteractor: BluetoothInteractor,
    private val bluetoothDevice: BluetoothDevice
) : ViewModel() {
    val state = MutableStateFlow("")

    init {
        viewModelScope.launch {
            val sb = StringBuilder()
            val buffer = ByteArray(256)

            bluetoothInteractor.connect(
                bluetoothDevice.address,
                UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            ) {
                while (true) {
                    val read = it.inputStream.read(buffer)
                    if (read > 0) {
                        sb.append(String(buffer, 0, read))
                        state.value = sb.toString()
                    }
                    delay(100)
                }
            }
        }
    }

}