plugins {
    id("ru.starfactory.convention.preset.core")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(libs.decompose.core)
                api(libs.decompose.jetbrains)

                api(libs.kodein.compose)

                implementation(project(":core:compose"))
                implementation(project(":core:logger"))
            }
        }
    }
}
