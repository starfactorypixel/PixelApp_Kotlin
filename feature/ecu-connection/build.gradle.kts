plugins {
    id("ru.starfactory.convention.preset.client-feature")
    id("org.jetbrains.compose")
    id("kotlin-parcelize") //TODO
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:serial"))
            }
        }
    }
}