plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradlePlugins.kotlin.core)
    implementation(libs.gradlePlugins.android)
    implementation(libs.gradlePlugins.jb.compose)
    implementation(libs.gradlePlugins.checkUpdates)
}
