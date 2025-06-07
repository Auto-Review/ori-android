// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.jetbrains.kotlin.jvm) apply false
  alias(libs.plugins.android.library) apply false

//    alias(libs.plugins.google.hilt.android) apply false
  id("com.google.dagger.hilt.android") version libs.versions.hilt apply false
  id("com.google.devtools.ksp") version "2.0.20-1.0.25"

  // google service
  id("com.google.gms.google-services") version "4.4.2" apply false
}