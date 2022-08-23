package ru.starfactory.pixel.ui.screen.request_permission

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.replaceCurrent
import kotlinx.coroutines.flow.receiveAsFlow
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.service.permission.Permission
//
//@Composable
//fun RequestPermissionView(permission: Permission, nextScreen: Screen) {
//    val viewModel: RequestPermissionViewModel = decomposeViewModelFactory(permission)
//    val navigation = LocalNavigation.current
//
//    LaunchedEffect(viewModel, navigation) {
//        viewModel.navigateNext.receiveAsFlow().collect { navigation.replaceCurrent(nextScreen) }
//    }
//
//    RequestPermissionContent(
//        onClickRequestPermission = viewModel::onClickRequestPermission
//    )
//}
//
//@Composable
//private fun RequestPermissionContent(onClickRequestPermission: () -> Unit = {}) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(Modifier.align(Alignment.Center)) {
//            Text(
//                text = "Для достуа к данной функции необходимо предоставить разрешения",
//                Modifier.padding(horizontal = 16.dp)
//            )
//            Button(
//                onClick = onClickRequestPermission,
//                Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 8.dp)
//            ) {
//                Text(text = "Предоставить")
//            }
//        }
//    }
//}