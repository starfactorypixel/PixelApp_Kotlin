package ru.starfactory.pixel.ui.screen.permission

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.service.permission.PermissionRequester
import ru.starfactory.pixel.service.permission.PermissionService
import ru.starfactory.pixel.service.permission.PermissionServiceImpl
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class PermissionViewModel(private val permissionService: PermissionService) : ViewModel(), PermissionRequester {
    val permissionRequest = Channel<String>() //TODO Sumin: тут скорее всего нужен полноценный стейт

    private val mutex = Mutex()
    private var continuation: Continuation<Boolean>? = null

    init {
        (permissionService as PermissionServiceImpl).permissionRequester = this
    }

    override fun onDestroy() {
        (permissionService as PermissionServiceImpl).permissionRequester = null
        super.onDestroy()
    }

    override suspend fun requestPermission(permission: String): Boolean {
        return mutex.withLock {
            coroutineScope {
                val viewModelLifecycleWeather = launch {
                    viewModelScope.coroutineContext[Job]!!.join()
                    throw RuntimeException("View model destroying")
                }

                val resultDeferred = async {
                    suspendCancellableCoroutine {
                        continuation = it
                    }
                }

                permissionRequest.send(permission)

                val result = resultDeferred.await()
                viewModelLifecycleWeather.cancel()
                result
            }
        }
    }

    fun onPermissionResult(result: Boolean) {
        val continuation = continuation
        if (continuation != null) {
            continuation.resume(result)
        } else {
            // TODO Sumin: record exception
        }
    }
}