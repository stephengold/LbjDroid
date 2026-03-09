// Gradle script to build the HelloLibbulletjme example app for Android

plugins {
    alias(libs.plugins.android.application) // to build Android libraries
}

android {
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    compileSdk = 36
    defaultConfig {
        applicationId = "com.github.stephengold.lbjdroid"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    namespace = "com.github.stephengold.lbjdroid"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation("com.github.stephengold", "Libbulletjme-Android",
            libs.versions.lbj.get(), "", "SpDebug", "aar"
    )
}
