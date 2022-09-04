plugins {
    `kotlin-dsl`
}

dependencies {
    // TODO подождать пока эта фича появится в гредле
    // а пока костыль вот отсюда https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.gradlePlugins.kotlin.core)
    implementation(libs.gradlePlugins.android)
    implementation(libs.gradlePlugins.jb.compose)
    implementation(libs.gradlePlugins.checkUpdates)
    implementation(libs.gradlePlugins.detekt)

    implementation(libs.gradlePlugins.google.services)
    implementation(libs.gradlePlugins.google.firebase.crashlytic)
}
