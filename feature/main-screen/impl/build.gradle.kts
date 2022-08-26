plugins {
    id("ru.starfactory.convention.preset.client-feature")
    id("org.jetbrains.compose")
    id("kotlin-parcelize") //TODO
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":feature:main-screen:api"))
                implementation(project(":feature:apps"))
                implementation(project(":feature:dashboard-screen"))
                implementation(project(":feature:settings-screen"))
            }
        }
    }
}

