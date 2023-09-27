plugins {
  `kotlin-dsl`
}

group = "achat.whatsappclone.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApplicationCompose") {
      id = "achat.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidApplication") {
      id = "achat.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
//    register("androidLibraryCompose") {
//      id = "achat.android.library.compose"
//      implementationClass = "AndroidLibraryComposeConventionPlugin"
//    }
//    register("androidLibrary") {
//      id = "achat.android.library"
//      implementationClass = "AndroidLibraryConventionPlugin"
//    }

    register("androidHilt") {
      id = "achat.android.hilt"
      implementationClass = "AndroidHiltConventionPlugin"
    }
  }
}
