plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradlePlugins.kotlin.core)
    implementation(libs.gradlePlugins.android)
    implementation(libs.gradlePlugins.jb.compose)
    implementation(libs.gradlePlugins.checkUpdates)

    implementation(libs.gradlePlugins.google.services)
    implementation(libs.gradlePlugins.google.firebase.crashlytic)
}
