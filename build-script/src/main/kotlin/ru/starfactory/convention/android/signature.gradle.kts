package ru.starfactory.convention.android

plugins {
    id("ru.starfactory.convention.android.application")
}

val pUseReleaseKeys: String by project
val useReleaseKeys = pUseReleaseKeys.toBoolean()

@Suppress("UnstableApiUsage")
android {
    signingConfigs {
        named("debug") {
            storeFile = rootProject.projectDir.resolve("keys").resolve("debug.jks")
            storePassword = "pixel_app"

            keyAlias = "pixel-android"
            keyPassword = "pixel_app"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}