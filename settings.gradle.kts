pluginManagement {
    includeBuild("build-dependency")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        google()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        google()
    }
}

rootProject.name = "AChat"
include(":app")
include(":core:navigation")
include(":features:emogif")
