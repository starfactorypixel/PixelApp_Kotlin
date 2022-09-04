plugins {
    id("ru.starfactory.convention.check-updates")
}

allprojects {
    afterEvaluate {//TODO Sumin: поправить этот костыль
        apply {
            plugin("ru.starfactory.convention.analyze.detekt")
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.register("ci") {
    dependsOn(":dependencyUpdates")
    dependsOn(":client:android:assembleDebug")
    dependsOn(":client:android:lintDebug")

    dependsOn(":client-wv:android:assembleDebug")
    dependsOn(":client-wv:android:lintDebug")
}

tasks.register("pr") {
    dependsOn(":client:android:assembleDebug")
    dependsOn(":client:android:lintDebug")

    dependsOn(":client-wv:android:assembleDebug")
    dependsOn(":client-wv:android:lintDebug")
}
