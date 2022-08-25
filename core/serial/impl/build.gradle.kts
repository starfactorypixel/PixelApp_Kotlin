plugins {
    id("ru.starfactory.convention.preset.core")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":core:serial:api"))
                implementation(project(":core:serial:usb"))

                implementation(project(":core:coroutines"))
                implementation(project(":core:di"))
                implementation(project(":core:logger"))
            }
        }
    }
}