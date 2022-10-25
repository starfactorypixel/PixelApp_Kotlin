package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuPrimaryState
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor

internal interface EcuSerialSourceConnectionInteractor : EcuSourceConnectionInteractor

@Suppress("MagicNumber")
internal class EcuSerialSourceConnectionInteractorImpl(
    private val lowLevelConnectionInteractor: EcuSerialSourceLowLevelConnectionInteractor,
) : EcuSerialSourceConnectionInteractor {

    override fun observePrimaryState(): Flow<EcuPrimaryState> {
        return lowLevelConnectionInteractor.observeConnection().flatMapLatest { emptyFlow() }
//        return connectionObservable
//            .flatMapLatest { connection ->
//                if (connection is Connection.Connected) {
//                    flow {
//                        while (true) {
//                            val ecuPrimaryState = connection.withEcuProtocol { ecuProtocol ->
//                                val speed = ecuProtocol.readUByteRegister(125)
//                                val voltage = ecuProtocol.readUIntRegister(174)
//
//                                EcuPrimaryState(
//                                    speed = speed.toInt(),
//                                    batteryCharge = voltage.toFloat() / 1000, // TODO this not a charge I know
//                                )
//                            }
//
//                            emit(ecuPrimaryState)
//
//                            delay(1000)
//                        }
//                    }
//                } else {
//                    emptyFlow()
//                }
//            }
//            .retry { true }
//            .onStart { emit(EcuPrimaryState(0, 0f)) }
//            .flowOn(Dispatchers.IO)
    }
}
