plugins {
    id("ru.starfactory.convention.preset.core")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":core:serial"))
                implementation(project(":core:usb"))

                implementation(project(":core:coroutines"))
                implementation(project(":core:di"))
                implementation(project(":core:logger"))
            }
        }

        named("androidMain") {
            dependencies {
                implementation(libs.serial.usb)
            }
        }
    }
}
