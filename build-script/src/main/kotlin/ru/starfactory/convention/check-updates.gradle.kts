package ru.starfactory.convention

import com.github.benmanes.gradle.versions.reporter.result.Result
import ru.starfactory.build_script.github.GithubActionLogger

plugins {
    id("com.github.ben-manes.versions")
}

val pIsGithubActions: String by project

tasks.dependencyUpdates.configure {
    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return !isStable
    }
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }

    if (pIsGithubActions.toBoolean()) {
        outputFormatter = closureOf<Result> {
            outdated.dependencies.forEach {
                GithubActionLogger.w(
                    "Library outdated: ${it.group}:${it.name} [${it.version} -> ${it.available.milestone}]"
                )
            }
        }
    }
}