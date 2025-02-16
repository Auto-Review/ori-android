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
include(":core:data-store")
include(":core:network")
include(":core:presentation")
include(":core:presentation-oauth")

include(":data-source:remote:server")

include(":data-source:local:data-store")

include(":domain:auth")
include(":data:auth")

include(":domain:code-post")
include(":data:code-post")
include(":presentation:code-post")
include(":presentation:auth")
