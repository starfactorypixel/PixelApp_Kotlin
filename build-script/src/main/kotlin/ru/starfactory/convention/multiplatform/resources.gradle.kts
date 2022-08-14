package ru.starfactory.convention.multiplatform

import dev.icerock.gradle.MRVisibility
import org.gradle.accessors.dm.LibrariesForLibs


plugins {
    // multiplatform-resources require at least one multiplatform target to apply
    id("ru.starfactory.convention.multiplatform.jvm")
    id("dev.icerock.mobile.multiplatform-resources")
}

val coreLibs = the<LibrariesForLibs>()

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(coreLibs.moko.resources.core)
                implementation(coreLibs.moko.resources.compose)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesVisibility = MRVisibility.Internal
}