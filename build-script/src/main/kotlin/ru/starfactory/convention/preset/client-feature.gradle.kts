package ru.starfactory.convention.preset

plugins {
    id("ru.starfactory.convention.multiplatform.jvm")
    id("ru.starfactory.convention.multiplatform.android-library")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:compose"))
                implementation(project(":core:coroutines"))
                implementation(project(":core:decompose"))
                implementation(project(":core:di"))
                implementation(project(":core:key-value-storage"))
                implementation(project(":core:navigation"))
            }
        }
    }
}
