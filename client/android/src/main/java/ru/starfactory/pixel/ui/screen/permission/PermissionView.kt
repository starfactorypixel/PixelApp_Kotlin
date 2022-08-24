package ru.starfactory.pixel.ui.screen.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.receiveAsFlow
import ru.starfactory.core.decompose.view_model.decomposeViewModel

//@Composable
//fun PermissionView(viewModel: PermissionViewModel = decomposeViewModel()) {
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        viewModel.onPermissionResult(isGranted)
//    }
//
//    LaunchedEffect(viewModel, launcher) {
//        viewModel.permissionRequest.receiveAsFlow().collect { permission ->
//            launcher.launch(permission)
//        }
//    }
//}