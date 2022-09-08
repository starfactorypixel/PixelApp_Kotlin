package ru.starfactory.convention.android

import com.android.build.api.variant.VariantOutput
import com.android.build.gradle.api.ApkVariantOutput
import com.android.build.gradle.api.BaseVariantOutput

plugins {
    id("ru.starfactory.convention.android.application")
}

val pVersion: String by project
val pBuildNumber: String by project

val fullVersion = "$pVersion.$pBuildNumber"
val baseVersionCode: Int = pBuildNumber.toInt()

android {
    defaultConfig {
        versionCode = baseVersionCode
        versionName = fullVersion
    }


    applicationVariants.configureEach {
        val applicationVariant = this

        val apkName = "Pixel-${applicationVariant.name}-$fullVersion"

        applicationVariant.outputs.configureEach {
            val applicationVariantOutput = this as ApkVariantOutput
            applicationVariantOutput.outputFileName = "${apkName}.apk"
        }
    }
}