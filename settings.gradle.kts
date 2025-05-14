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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://www.googleapis.com/download/storage/v1/b/google-api-java-client/o/")
            content {
                includeGroup("com.google.apis")
            }
        }
    }
}

rootProject.name = "Agenda_app_kotlin"
include(":app")
 