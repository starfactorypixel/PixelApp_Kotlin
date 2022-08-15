plugins {
    id("ru.starfactory.convention.preset.core")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(libs.multiplatformSettings.core)
                api(libs.multiplatformSettings.coroutines)

                implementation(project(":core:coroutines"))
                implementation(project(":core:di"))
            }
        }
    }
}