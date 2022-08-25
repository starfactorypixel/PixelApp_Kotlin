package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelize
import org.kodein.di.DI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance

@Parcelize
object SelectSourceScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        val viewModel: SelectSourceViewModel =
            decomposeViewModel(di, componentContext.instanceKeeper)
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                SelectSourceView(viewModel)
            }
        }
    }
}
