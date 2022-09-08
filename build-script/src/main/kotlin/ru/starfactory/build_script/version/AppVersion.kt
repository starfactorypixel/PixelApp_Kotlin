package ru.starfactory.build_script.version

import org.gradle.api.Project
import ru.starfactory.build_script.git.gitGetLatestVersionTag

data class AppVersion(
    val raw: String,

    val major: Int,
    val minor: Int,
    val path: Int,

    val commitsBehindLastRelease: Int,
    val lastCommitHash: String?
) {
    val androidVersionCode: Int = major * 100_000 + minor * 100 + path
}

private val TAG_REGEXP = Regex("^v(?<major>\\d+)\\.(?<minor>\\d{1,3})\\.(?<path>\\d{1,2})(-(?<commitsCount>\\d{3,4}))?(-(?<lastCommitHash>[a-z\\d]+))?\n?\$")


fun Project.appVersion(): AppVersion {
    val rawTag = gitGetLatestVersionTag()
    val matchResult = TAG_REGEXP.matchEntire(rawTag)
    check(matchResult != null) { "Tag not matching regexp, tag=$rawTag" }
    val groups = matchResult.groups
    return AppVersion(
        raw = rawTag.trim(),
        major = groups["major"]!!.value.toInt(),
        minor = groups["minor"]!!.value.toInt(),
        path = groups["path"]!!.value.toInt(),
        commitsBehindLastRelease = groups["commitsCount"]?.value?.toInt() ?: 0,
        lastCommitHash = groups["lastCommitHash"]?.value
    )
}