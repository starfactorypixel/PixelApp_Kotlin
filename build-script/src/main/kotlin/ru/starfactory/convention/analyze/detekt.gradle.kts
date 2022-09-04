package ru.starfactory.convention.analyze

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val libs = the<LibrariesForLibs>()

val detektConfigFile = rootProject.projectDir.resolve("config/detekt").resolve("detekt.yml")
check(detektConfigFile.isFile)

// Конфигурирем на уровне тасок, а не на уровне плагина, так как таски созданные в ручную
// не подтягивают дефолтные значения из конфигурации плагина
tasks.withType<Detekt>().configureEach {
    autoCorrect = true
    parallel = true
    buildUponDefaultConfig = true
    config.setFrom(files(detektConfigFile))
}

// Исправляем путь к файлам только для дефолтных detekt тасок
tasks.named<Detekt>("detekt").configure {
    source = fileTree(project.projectDir) {
        include("src/**/*")
        include("build.gradle.kts")
        include("settings.gradle.kts")
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}