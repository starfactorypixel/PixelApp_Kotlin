package ru.starfactory.pixel.ecu_connection.domain.source

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.demo.EcuDemoSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.repository.EcuSourceRepository

interface EcuSourceInteractor {
    fun observeSources(): Flow<List<Source>>

    fun observeSelectedSource(): Flow<Source?>

    suspend fun selectSource(source: Source)
    suspend fun selectSource(sourceId: String)
    fun observeDefaultSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor>
}

internal class EcuSourceInteractorImpl(
    private val ecuDemoSourceConnectionInteractor: EcuDemoSourceConnectionInteractor,
    private val scope: CoroutineScope,
    private val serialInteractor: SerialInteractor,
    private val ecuSourceRepository: EcuSourceRepository,
) : EcuSourceInteractor {

    private val sourcesObservable: Flow<List<Source>> =
        combine(
            serialSourcesObservable(),
            demoSourcesObservable()
        ) { sources -> sources.flatMap { it } }
            .shareDefault(scope)

    override fun observeSources(): Flow<List<Source>> = sourcesObservable

    override fun observeDefaultSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor> {
        return flowOf(ecuDemoSourceConnectionInteractor)
    }

    override fun observeSelectedSource(): Flow<Source?> {
        return combine(
            observeSources(),
            ecuSourceRepository.observeSelectedSourceId()
        ) { sources, sourceId ->
            sources.find { it.id == sourceId }
        }
    }

    override suspend fun selectSource(source: Source) = selectSource(source.id)

    override suspend fun selectSource(sourceId: String) {
        ecuSourceRepository.saveSelectedSourceId(sourceId)
    }

    private fun serialSourcesObservable(): Flow<List<Source>> {
        return serialInteractor.observeSerialDevices()
            .map { devices ->
                devices.map {
                    Source(
                        sourceType = it.type.toSourceType(),
                        id = it.id.rawId,
                        name = it.name
                    )
                }
            }
    }

    private fun demoSourcesObservable(): Flow<List<Source>> = flowOf(listOf(Source(SourceType.DEMO, "demo", "Demo")))
}

private fun SerialDeviceType.toSourceType(): SourceType = when (this) {
    SerialDeviceType.USB -> SourceType.USB_SERIAL
    SerialDeviceType.BLUETOOTH -> SourceType.BLUETOOTH
}
