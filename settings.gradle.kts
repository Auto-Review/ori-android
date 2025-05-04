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
include(":core:data-store-manager")
include(":core:token-manager")
include(":core:presentation")
include(":core:presentation-oauth")

include(":data-source:remote:server")
include(":data-source:local:data-store")

include(":domain:local-setting")
include(":data:local-setting")

include(":domain:auth-user")
include(":data:auth-user")
include(":presentation:auth")

include(":domain:code-post")
include(":data:code-post")
include(":presentation:code-post")

include(":domain:notification")
include(":data:notification")
include(":presentation:schedule")

include(":domain:til")
include(":data:til")
include(":presentation:til")

include(":domain:user")
include(":data:user")
include(":presentation:my")