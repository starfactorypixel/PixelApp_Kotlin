plugins {
    id("ru.starfactory.convention.check-updates")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.register("ci") {
    dependsOn(":dependencyUpdates")
    dependsOn(":client:android:assembleDebug")
    dependsOn(":client:android:lintDebug")

    dependsOn(":client:android-wv:assembleDebug")
    dependsOn(":client:android-wv:lintDebug")
}

tasks.register("pr") {
    dependsOn(":client:android:assembleDebug")
    dependsOn(":client:android:lintDebug")

    dependsOn(":client:android-wv:assembleDebug")
    dependsOn(":client:android-wv:lintDebug")
}
