plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.dd2d.ori_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dd2d.ori_android"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":core:core"))
    implementation(project(":core:network"))
    implementation(project(":core:data-store-manager"))
    implementation(project(":core:token-manager"))
    implementation(project(":core:presentation"))
    implementation(project(":core:presentation-oauth"))

    implementation(project(":data-source:local:data-store"))

    implementation(project(":data-source:remote:server"))

    implementation(project(":domain:local-setting"))
    implementation(project(":data:local-setting"))
    implementation(project(":domain:auth-user"))
    implementation(project(":data:auth-user"))

    implementation(project(":presentation:auth"))

    implementation(project(":domain:user"))
    implementation(project(":data:user"))
    implementation(project(":presentation:my"))

    implementation(project(":domain:schedule"))
    implementation(project(":data:schedule"))
    implementation(project(":presentation:schedule"))

    implementation(project(":domain:code-post"))
    implementation(project(":data:code-post"))
    implementation(project(":presentation:code-post"))

    implementation(project(":domain:til"))
    implementation(project(":data:til"))
    implementation(project(":presentation:til"))
}