package ru.starfactory.pixel.service.permission

import android.Manifest

enum class Permission(val raw: String) {
    BLUETOOTH(Manifest.permission.BLUETOOTH),
}