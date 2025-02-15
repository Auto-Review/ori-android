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
    }
}

rootProject.name = "OriAndroid"
include(":app")

include(":core:core")
include(":core:network")
include(":core:presentation")
include(":core:presentation-oauth")

include(":domain:code-post")
include(":data:code-post")
include(":presentation:code-post")
