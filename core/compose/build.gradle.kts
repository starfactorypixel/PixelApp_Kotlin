plugins {
    id("ru.starfactory.convention.preset.core")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)

                // api(compose.uiTooling)
                // api(compose.preview)
            }
        }
        named("jvmMain") {
            dependencies {
                api(compose.desktop.currentOs)
            }
        }
        named("androidMain") {
            dependencies {
                api(libs.android.activity.compose)
                api(libs.android.compose.foundation)
            }
        }
    }
}