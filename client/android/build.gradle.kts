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
        kotlinCompilerExtensionVersion = libs.versions.android.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:compose"))
    implementation(project(":core:coroutines"))
    implementation(project(":core:decompose"))
    implementation(project(":core:di"))
    implementation(project(":core:key-value-storage"))
    implementation(project(":core:navigation"))
    implementation(project(":core:uikit"))

    implementation(project(":feature:main-screen"))
    implementation(project(":feature:theming"))

    implementation(libs.decompose.jetpack)
    implementation(libs.decompose.android)

    implementation(libs.android.datastore.preferences)
    implementation(libs.android.activity.core)

    implementation(libs.serial.usb)

    implementation(libs.android.core)
    implementation(libs.android.compose.ui)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.preview)
}
