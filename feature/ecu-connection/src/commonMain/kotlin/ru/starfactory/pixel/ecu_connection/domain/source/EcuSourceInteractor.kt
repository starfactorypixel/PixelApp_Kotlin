package ru.starfactory.pixel.ecu_connection.domain.source

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.demo.EcuDemoSourceConnectionInteractor

interface EcuSourceInteractor {
    fun observeSources(): Flow<List<Source>>
    fun observeDefaultSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor>
}

internal class EcuSourceInteractorImpl(
    private val ecuDemoSourceConnectionInteractor: EcuDemoSourceConnectionInteractor,
    private val scope: CoroutineScope,
    private val serialInteractor: SerialInteractor,
) : EcuSourceInteractor {

    private val sourcesObservable: Flow<List<Source>> =
        combine(
            serialSourcesObservable(),
            demoSourcesObservable()
        ) { sources -> sources.flatMap { it } }
            .shareDefault(scope)

    private fun serialSourcesObservable(): Flow<List<Source>> {
        return serialInteractor.observeSerialDevices()
            .map { devices ->
                devices.map {
                    Source.Serial(
                        sourceType = it.type.toSourceType(),
                        id = it.id.toString(),
                        name = it.name
                    )
                }
            }
    }

    private fun demoSourcesObservable(): Flow<List<Source>> = flowOf(listOf(Source.Demo))

    override fun observeSources(): Flow<List<Source>> = sourcesObservable

    override fun observeDefaultSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor> {
        return flowOf(ecuDemoSourceConnectionInteractor)
    }
}

private fun SerialDeviceType.toSourceType(): SourceType = when (this) {
    SerialDeviceType.USB -> SourceType.USB_SERIAL
}