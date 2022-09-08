package ru.starfactory.convention.android

import com.android.build.gradle.api.ApkVariantOutput
import ru.starfactory.build_script.version.appVersion

plugins {
    id("ru.starfactory.convention.android.application")
}

val appVersion = project.appVersion()
logger.error("Pixel app version: $appVersion")

android {
    defaultConfig {
        versionCode = appVersion.androidVersionCode
        versionName = appVersion.raw
    }

    applicationVariants.configureEach {
        val applicationVariant = this

        val apkName = "Pixel-${applicationVariant.name}-${appVersion.raw}"

        applicationVariant.outputs.configureEach {
            val applicationVariantOutput = this as ApkVariantOutput
            applicationVariantOutput.outputFileName = "${apkName}.apk"
        }
    }
}