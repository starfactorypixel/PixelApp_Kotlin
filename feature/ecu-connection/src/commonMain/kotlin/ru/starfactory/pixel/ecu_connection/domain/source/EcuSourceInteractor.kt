package ru.starfactory.pixel.ecu_connection.domain.source

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.retry
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.demo.EcuDemoSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.serial.EcuSerialSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.serial.EcuSerialSourceConnectionInteractorFactory
import ru.starfactory.pixel.ecu_connection.domain.repository.EcuSourceRepository

interface EcuSourceInteractor {
    fun observeSources(): Flow<List<Source>>

    fun observeSelectedSource(): Flow<Source?>

    suspend fun selectSource(source: Source)
    suspend fun selectSource(sourceId: String)
    fun observeSelectedSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor?>
}

internal class EcuSourceInteractorImpl(
    private val ecuDemoSourceConnectionInteractor: EcuDemoSourceConnectionInteractor,
    private val ecuSerialSourceConnectionInteractorFactory: EcuSerialSourceConnectionInteractorFactory,
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

    private val selectedSourceObservable: Flow<Source?> =
        combine(
            observeSources(),
            ecuSourceRepository.observeSelectedSourceId()
        ) { sources, sourceId ->
            sources.find { it.id == sourceId }
        }
            .shareDefault(scope)

    private val selectedSourceConnectionInteractorObservable: Flow<EcuSourceConnectionInteractor?> =
        selectedSourceObservable
            .flatMapLatest { source ->
                when {
                    source == null -> flowOf(null)
                    source.sourceType == SourceType.DEMO -> flowOf(ecuDemoSourceConnectionInteractor)
                    source.sourceType == SourceType.USB_SERIAL || source.sourceType == SourceType.BLUETOOTH ->
                        serialSourceConnectionInteractorObservable(source.id)
                    else -> error("Unreachable code, source: $source")
                }
            }
            .shareDefault(scope)

    override fun observeSources(): Flow<List<Source>> = sourcesObservable

    override fun observeSelectedSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor?> {
        return selectedSourceConnectionInteractorObservable
    }

    override fun observeSelectedSource(): Flow<Source?> = selectedSourceObservable

    override suspend fun selectSource(source: Source) = selectSource(source.id)

    override suspend fun selectSource(sourceId: String) {
        ecuSourceRepository.saveSelectedSourceId(sourceId)
    }

    private fun serialSourcesObservable(): Flow<List<Source>> {
        return serialInteractor.observeSerialDevices()
            .map { devices ->
                devices.map {
                    Source(
                        sourceType = it.info.type.toSourceType(),
                        id = it.id,
                        name = it.info.name
                    )
                }
            }
    }

    private fun serialSourceConnectionInteractorObservable(deviceId: String): Flow<EcuSerialSourceConnectionInteractor?> {
        return flowOf(null) //TODO Sumin
    }

    private fun demoSourcesObservable(): Flow<List<Source>> = flowOf(listOf(Source(SourceType.DEMO, "demo", "Demo")))
}

private fun SerialDeviceType.toSourceType(): SourceType = when (this) {
    SerialDeviceType.USB -> SourceType.USB_SERIAL
    SerialDeviceType.BLUETOOTH -> SourceType.BLUETOOTH
}
