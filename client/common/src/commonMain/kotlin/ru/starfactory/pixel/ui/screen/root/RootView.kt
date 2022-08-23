package ru.starfactory.pixel.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import org.kodein.di.bindSingleton
import org.kodein.di.compose.subDI
import ru.starfactory.core.compose.LocalConfigurationHolder
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.pixel.theming.ui.theme.ThemeView

@Composable
fun RootView(componentContext: ComponentContext) {

    subDI(diBuilder = {
        bindSingleton { componentContext }
    }) {
        val viewModel: RootViewModel = decomposeViewModel()
        LocalConfigurationHolder {

            // PermissionView()

            ThemeView {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationContentView(viewModel.childStack)
                }
            }
        }
    }
}
