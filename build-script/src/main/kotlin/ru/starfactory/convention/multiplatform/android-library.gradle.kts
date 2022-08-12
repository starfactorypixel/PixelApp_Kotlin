package ru.starfactory.convention.multiplatform

plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
    id("ru.starfactory.convention.android.base")
}

kotlin {
    android()
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}