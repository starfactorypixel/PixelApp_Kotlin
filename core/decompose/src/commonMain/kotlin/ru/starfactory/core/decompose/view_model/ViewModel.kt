package ru.starfactory.core.decompose.view_model

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class ViewModel : InstanceKeeper.Instance {
    protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        // Log.i("ViewModel", "Create ${this::class.simpleName}") TODO add shared logger
    }

    override fun onDestroy() {
        // Log.i("ViewModel", "Destroy ${this::class.simpleName}")
        viewModelScope.cancel()
    }
}
