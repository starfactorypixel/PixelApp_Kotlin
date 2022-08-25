package ru.starfactory.core.coroutines

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow

fun Context.observeBroadcastIntents(intentFilter: IntentFilter, buffered: Boolean): Flow<Intent> {
    val context = this
    return callbackFlow {
        val receiver = Receiver(this)
        context.registerReceiver(receiver, intentFilter)
        this.awaitClose { context.unregisterReceiver(receiver) }
    }
        .buffer(if (buffered) Channel.BUFFERED else Channel.CONFLATED)
}

private class Receiver(private val producerScope: ProducerScope<Intent>) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        producerScope.trySend(intent)
    }
}