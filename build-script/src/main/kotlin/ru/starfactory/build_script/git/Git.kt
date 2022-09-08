package ru.starfactory.build_script.git

import org.gradle.api.Project
import org.gradle.process.internal.ExecException
import java.io.ByteArrayOutputStream

fun Project.gitGetLatestVersionTag(): String {
    val data = ByteArrayOutputStream()
    val error = ByteArrayOutputStream()
    try {
        val result = exec {
            executable = "git"
            args = listOf("describe", "--tags", "--match", "v*")
            standardOutput = data
            errorOutput = error
        }
        check(result.exitValue == 0) {
            "git return non zero value: ${result.exitValue}, data='$data', error='$error'"
        }
    } catch (e: ExecException) {
        logger.error("exec failed, data='$data', error='$error'")
        throw e
    }
    return data.toString()
}