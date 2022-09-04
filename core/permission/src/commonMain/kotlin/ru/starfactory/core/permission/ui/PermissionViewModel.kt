package ru.starfactory.core.permission.ui

import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.permission.service.PermissionRequester
import ru.starfactory.core.permission.service.PermissionService
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class PermissionViewModel internal constructor(private val permissionService: PermissionService) : ViewModel(), PermissionRequester {
    val permissionRequest = Channel<String>() // TODO Sumin: тут скорее всего нужен полноценный стейт

    private val mutex = Mutex()
    private var continuation: Continuation<Boolean>? = null

    init {
        permissionService.permissionRequester = this
    }

    override fun onDestroy() {
        permissionService.permissionRequester = null
        super.onDestroy()
    }

    override suspend fun requestPermission(permission: String): Boolean {
        return mutex.withLock {
            coroutineScope {
                val viewModelLifecycleWeather = launch {
                    viewModelScope.coroutineContext[Job]!!.join()
                    error("View model destroying")
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
