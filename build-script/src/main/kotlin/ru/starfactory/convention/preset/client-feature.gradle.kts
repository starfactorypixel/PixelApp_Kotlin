package ru.starfactory.convention.preset

import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("ru.starfactory.convention.multiplatform.jvm")
    id("ru.starfactory.convention.multiplatform.android-library")
}

val libs = the<LibrariesForLibs>()

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:compose"))
                implementation(project(":core:coroutines"))
                implementation(project(":core:decompose"))
                implementation(project(":core:di"))
                implementation(project(":core:key-value-storage"))
                implementation(project(":core:logger"))
                implementation(project(":core:navigation"))
                implementation(project(":core:permission"))
                implementation(project(":core:uikit"))

                implementation(libs.reorderable)
            }
        }
    }
}
