plugins {
  id("java-library")
  alias(libs.plugins.jetbrains.kotlin.jvm)

  id("com.google.devtools.ksp")
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
  implementation(libs.hilt.core)
  ksp(libs.hilt.android.compiler)
  implementation(libs.kotlin.stdlib) // Kotlin 표준 라이브러리 추가
  implementation(libs.kotlinx.coroutines.core) // 코루틴 라이브러리

  implementation(project(":core:core"))
  implementation(project(":core:data-store-manager"))
  implementation(project(":core:token-manager"))

  implementation(project(":domain:local-setting"))
}
