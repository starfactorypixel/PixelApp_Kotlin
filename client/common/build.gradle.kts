plugins {
    id("ru.starfactory.convention.multiplatform.android-library")
    id("ru.starfactory.convention.multiplatform.ios-framework")
    id("ru.starfactory.convention.multiplatform.jvm")
    id("ru.starfactory.convention.multiplatform.resources")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:compose"))
                api(project(":core:coroutines"))
                api(project(":core:decompose"))
                api(project(":core:di"))
                api(project(":core:navigation"))
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "ru.starfactory.pixel"
}
