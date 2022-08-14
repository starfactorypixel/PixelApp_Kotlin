plugins {
    id("ru.starfactory.convention.preset.core") //TODO
    id("org.jetbrains.compose")
}

kotlin {
    iosX64("uikitX64") {
        binaries {
            framework() {
//                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }
    iosArm64("uikitArm64") {
        binaries {
            framework() {
//                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
                // TODO: the current compose binary surprises LLVM, so disable checks for now.
                freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
            }
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }
//        val macosMain by creating {
//            dependsOn(iosMain)
//        }
//        val macosX64Main by getting {
//            dependsOn(macosMain)
//        }
//        val macosArm64Main by getting {
//            dependsOn(macosMain)
//        }
        val uikitMain by creating {
            dependsOn(iosMain)
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }
    }
}

//compose.experimental {
//    uikit.application {
//        bundleIdPrefix = "ru.starfactory"
//        projectName = "Pixel"
//        deployConfigurations {
//            simulator("IPhone13") {
//                //Usage: ./gradlew iosDeployIPhone13Debug
//                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPHONE_13_PRO
//            }
//            simulator("IPadUI") {
//                //Usage: ./gradlew iosDeployIPadUIDebug
//                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPAD_MINI_6th_Gen
//            }
//        }
//    }
//}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

kotlin {
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}
