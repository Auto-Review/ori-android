plugins {
  id("java-library")
  alias(libs.plugins.jetbrains.kotlin.jvm)

  id("com.google.devtools.ksp")
  kotlin("plugin.serialization") version "2.0.20"
}
java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
  compilerOptions {
    jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
  }
}

dependencies {
  implementation(libs.kotlin.stdlib) // Kotlin 표준 라이브러리 추가
  implementation(libs.kotlinx.coroutines.core) // 코루틴 라이브러리

  implementation(libs.ktor.client.core)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.hilt.core)
  ksp(libs.hilt.android.compiler)

  implementation(project(":core:core"))
  implementation(project(":core:token-manager"))
}