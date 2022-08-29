plugins {
    id("ru.starfactory.convention.preset.client-feature")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":core:apps"))
                api(project(":core:compose"))
                api(project(":core:coroutines"))
                api(project(":core:decompose"))
                api(project(":core:di"))
                api(project(":core:key-value-storage"))
                api(project(":core:logger"))
                api(project(":core:navigation"))
                api(project(":core:serial"))
                api(project(":core:serial:usb"))
                api(project(":core:uikit"))
                api(project(":core:usb"))

                api(project(":feature:apps"))
                api(project(":feature:dashboard-screen"))
                api(project(":feature:ecu-connection"))
                api(project(":feature:main-screen:impl"))
                api(project(":feature:settings-screen"))
                api(project(":feature:theming"))
            }
        }
    }
}
