package ru.starfactory.convention.android

import ru.starfactory.build_script.utils.android

android {
    setCompileSdkVersion(32)

    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}