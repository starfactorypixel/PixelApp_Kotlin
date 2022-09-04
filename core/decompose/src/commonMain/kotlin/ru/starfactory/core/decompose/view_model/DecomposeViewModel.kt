package ru.starfactory.core.decompose.view_model

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.direct
import org.kodein.di.factory
import org.kodein.di.instance

inline fun <reified VM : ViewModel> decomposeViewModel(di: DI, instanceKeeper: InstanceKeeper, key: Any = VM::class): VM {
    return decomposeViewModel(di, instanceKeeper, key) { instance() }
}

inline fun <reified VM : ViewModel, reified A : Any> decomposeViewModelFactory(
    di: DI,
    instanceKeeper: InstanceKeeper,
    argument: A,
    key: Any = VM::class
): VM {
    return decomposeViewModel(di, instanceKeeper, key) { factory<A, VM>()(argument) }
}

inline fun <reified VM : ViewModel> decomposeViewModel(
    di: DI,
    instanceKeeper: InstanceKeeper,
    key: Any = VM::class,
    factory: DirectDI.() -> VM,
): VM {
    return instanceKeeper.getOrCreate(key) { di.direct.factory() }
}
