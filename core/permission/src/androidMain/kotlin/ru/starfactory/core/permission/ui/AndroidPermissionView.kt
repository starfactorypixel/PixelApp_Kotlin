package ru.starfactory.core.permission.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
actual fun PermissionView(viewModel: PermissionViewModel) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onPermissionResult(isGranted)
    }

    LaunchedEffect(viewModel, launcher) {
        viewModel.permissionRequest.receiveAsFlow().collect { permission ->
            launcher.launch(permission)
        }
    }
}