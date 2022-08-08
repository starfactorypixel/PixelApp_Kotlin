package ru.starfactory.pixel.service.permission

import android.Manifest

enum class Permission(val raw: String) {
    BLUETOOTH_CONNECT(Manifest.permission.BLUETOOTH_CONNECT),
}