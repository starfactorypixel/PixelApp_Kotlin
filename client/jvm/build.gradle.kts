plugins {
    id("ru.starfactory.convention.multiplatform.jvm")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":client:common"))
                implementation(libs.kotlin.coroutines.swing)
            }
        }
    }
}
