package ru.starfactory.convention.android

import ru.starfactory.build_script.utils.android
import ru.starfactory.build_script.utils.kotlinOptions

plugins {
    id("ru.starfactory.convention.android.base")
    kotlin("android")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}