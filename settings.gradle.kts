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
        google() // to find libraries released to Google's Maven Repository
        mavenCentral() // to find libraries released to the Maven Central repository
        //mavenLocal() // to find libraries installed locally
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

// subprojects:
include("HelloLibbulletjme")
