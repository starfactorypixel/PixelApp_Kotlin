plugins {
    id("ru.starfactory.convention.preset.core")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:serial"))
                implementation(project(":core:bluetooth"))

                implementation(project(":core:coroutines"))
                implementation(project(":core:di"))
                implementation(project(":core:logger"))
            }
        }
    }
}