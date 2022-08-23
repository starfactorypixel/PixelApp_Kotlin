package ru.starfactory.core.decompose.view_model

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.kodein.di.*

inline fun <reified VM : ViewModel> decomposeViewModel(di: DI, context: ComponentContext, key: Any = VM::class): VM {
    return decomposeViewModel(di, context, key) { instance() }
}

//inline fun <reified VM : ViewModel, reified A : Any> decomposeViewModelFactory(
//    di: DI,
//    context: ComponentContext,
//    argument: A,
//    key: Any = VM::class
//): VM {
//    return decomposeViewModelManual(di, context, key) { factory<A, VM>()(argument) }
//}

inline fun <reified VM : ViewModel> decomposeViewModel(
    di: DI,
    context: ComponentContext,
    key: Any = VM::class,
    factory: DirectDI.() -> VM,
): VM {
    return context.instanceKeeper.getOrCreate(key) { di.direct.factory() }
}
