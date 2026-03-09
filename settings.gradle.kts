// global build settings shared by all LbjDroid subprojects

rootProject.name = "LbjDroid"

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral() // to find libraries released to the Maven Central repository
        //mavenLocal() // to find libraries installed locally
    }
}

// subprojects:
include("HelloLibbulletjme")
