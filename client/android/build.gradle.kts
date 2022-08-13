@file:Suppress("UnstableApiUsage")

plugins {
    id("ru.starfactory.convention.android.application")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        applicationId = "ru.starfactory.pixel"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.core.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies { // TODO move to version catalog
    implementation(project(":core:compose"))
    implementation(project(":core:coroutines"))
    implementation(project(":core:decompose"))
    implementation(project(":core:di"))

    implementation(libs.kodein.compose)

    implementation(libs.decompose.jetpack)
    implementation(libs.decompose.android)

    implementation(libs.android.datastore.preferences)
    implementation(libs.android.activity.core)

    implementation(libs.serial.usb)

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.compose.ui:ui:${libs.versions.compose.core.get()}")
    implementation("androidx.compose.material:material:${libs.versions.compose.core.get()}")
    implementation("androidx.compose.ui:ui-tooling-preview:${libs.versions.compose.core.get()}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${libs.versions.compose.core.get()}")
    debugImplementation("androidx.compose.ui:ui-tooling:${libs.versions.compose.core.get()}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${libs.versions.compose.core.get()}")
}