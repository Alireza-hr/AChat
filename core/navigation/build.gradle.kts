plugins {
    id("achat.android.application")
    id("achat.android.hilt")
}
android {
    namespace = "com.alirezahr.achatt"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    api(libs.androidx.navigation.compose)
}
