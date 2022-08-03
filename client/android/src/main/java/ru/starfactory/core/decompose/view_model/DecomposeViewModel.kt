package ru.starfactory.core.decompose.view_model

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.kodein.di.compose.localDI
import org.kodein.di.direct
import org.kodein.di.instance
import ru.starfactory.core.decompose.LocalComponentContext

@Composable
inline fun <reified VM : ViewModel> decomposeViewModel(key: Any = VM::class): VM {
    val context = LocalComponentContext.current
    val di = localDI()
    return context.instanceKeeper.getOrCreate(key) { di.direct.instance() }
}
