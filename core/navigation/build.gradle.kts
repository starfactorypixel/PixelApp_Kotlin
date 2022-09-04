plugins {
    id("ru.starfactory.convention.preset.core")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:compose"))
                implementation(project(":core:decompose"))
            }
        }
    }
}
