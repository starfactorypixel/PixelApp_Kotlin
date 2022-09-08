package ru.starfactory.build_script.git

import org.gradle.api.Project
import java.io.ByteArrayOutputStream

fun Project.gitGetLatestVersionTag(): String {
    val data = ByteArrayOutputStream()
    val result = exec {
        executable = "git"
        args = listOf("describe", "--tags", "--match", "v*")
        standardOutput = data
    }
    check(result.exitValue == 0) { "git return non zero value: ${result.exitValue}" }
    return data.toString()
}