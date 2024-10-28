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

rootProject.name = "exchanger"
include(":app")
include(":core")
include(":exchange:impl")
include(":exchange:api")
include(":converter:impl")
include(":converter:api")
include(":common:core")
include(":ui:exchange")
include(":ui:foundation")
include(":ui:converter")
