package ru.starfactory.core.decompose.view_model

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.kodein.di.*
import org.kodein.di.compose.localDI

@Composable
inline fun <reified VM : ViewModel> decomposeViewModel(key: Any = VM::class): VM {
    return decomposeViewModelManual({ di.direct.instance() }, key)
}

@Composable
inline fun <reified VM : ViewModel, reified A : Any> decomposeViewModelFactory(argument: A, key: Any = VM::class): VM {
    return decomposeViewModelManual({ di.direct.factory<A, VM>()(argument) }, key)
}

@Composable
inline fun <reified VM : ViewModel> decomposeViewModelManual(factory: DirectDI.() -> VM, key: Any = VM::class): VM {
    val di = localDI()
    val context: ComponentContext by di.instance()
    return context.instanceKeeper.getOrCreate(key) { di.direct.factory() }
}
