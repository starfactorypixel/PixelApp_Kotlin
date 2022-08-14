plugins {
    id("ru.starfactory.convention.multiplatform.android-library")
    id("ru.starfactory.convention.multiplatform.ios-framework")
    id("ru.starfactory.convention.multiplatform.jvm")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
            }
        }
    }
}

