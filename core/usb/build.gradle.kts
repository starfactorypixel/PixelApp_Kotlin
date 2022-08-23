plugins {
    id("ru.starfactory.convention.preset.core")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:coroutines"))
                implementation(project(":core:di"))
            }
        }
    }
}