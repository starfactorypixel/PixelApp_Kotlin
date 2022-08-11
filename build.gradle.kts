plugins {
    id("ru.starfactory.convention.check-updates")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.register("ci") {
    dependsOn(":dependencyUpdates")
    dependsOn(":client:android:assembleDebug")
}

tasks.register("pr") {
    dependsOn(":client:android:assembleDebug")
}
