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
    ":core:compose",
    ":core:coroutines",
    ":core:decompose",
    ":core:di",
    ":core:key-value-storage",
    ":core:navigation",
    ":core:uikit",
)

include(
    ":feature:dashboard-screen",
    ":feature:main-screen",
    ":feature:theming",
)

include(":client:android")

include(":client-wv:android")