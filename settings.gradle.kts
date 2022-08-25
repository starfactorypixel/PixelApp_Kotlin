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
    ":core:compose",
    ":core:coroutines",
    ":core:decompose",
    ":core:di",
    ":core:key-value-storage",
    ":core:logger",
    ":core:navigation",
    ":core:uikit",
    ":core:usb",
)

include(
    ":feature:apps",
    ":feature:dashboard-screen",
    ":feature:ecu-connection",
    ":feature:main-screen:api",
    ":feature:main-screen:impl",
    ":feature:theming",
)

include(
    ":client:common",
    ":client:android",
    ":client:jvm",
)

include(":client-wv:android")