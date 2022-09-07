// TODO убрать когда апи станет стабильным
@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-script")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}
rootProject.name = "pixel"

include(
    ":core:apps",
    ":core:bluetooth",
    ":core:compose",
    ":core:coroutines",
    ":core:decompose",
    ":core:di",
    ":core:key-value-storage",
    ":core:logger",
    ":core:navigation",
    ":core:permission",
    ":core:serial",
    ":core:serial:bluetooth",
    ":core:serial:usb",
    ":core:uikit",
    ":core:usb",
)

include(
    ":feature:apps",
    ":feature:dashboard-screen",
    ":feature:ecu-connection",
    ":feature:ecu-protocol",
    ":feature:main-screen:api",
    ":feature:main-screen:impl",
    ":feature:settings-screen",
    ":feature:theming",
)

include(
    ":client:common",
    ":client:android",
    ":client:jvm",
)

include(":client-wv:android")
