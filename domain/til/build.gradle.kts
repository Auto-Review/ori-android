plugins {
  id("java-library")
  alias(libs.plugins.jetbrains.kotlin.jvm)
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
  implementation(project(":core:core"))
}
