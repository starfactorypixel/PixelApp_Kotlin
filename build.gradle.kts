buildscript {
    repositories { // TODO
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradlePlugins.kotlin.core)
        classpath(libs.gradlePlugins.android)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.register("ci") {
    dependsOn(":client:android:assembleDebug")
}

tasks.register("pr") {
    dependsOn(":client:android:assembleDebug")
}
