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

        if (useReleaseKeys) {
            create("release") {
                val password = System.getenv("RELEASE_KEYSTORE_PASSWORD")
                check(password != null) { "environment variable RELEASE_KEYSTORE_PASSWORD not found" }

                storeFile = rootProject.projectDir.resolve("keys").resolve("release.jks")
                storePassword = password

                keyAlias = "pixel-android"
                keyPassword = password
            }
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            signingConfig = signingConfigs.getByName(if (useReleaseKeys) "release" else "debug")
        }
    }
}