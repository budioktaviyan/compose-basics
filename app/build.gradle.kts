@file:Suppress("UnstableApiUsage")
@file:SuppressLint("GradleDependency")

import android.annotation.SuppressLint

plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  namespace = "id.android.basics.compose"
  compileSdk = 33
  buildToolsVersion = "33.0.2"

  defaultConfig {
    applicationId = "id.android.basics.compose"
    minSdk = 30
    targetSdk = 33
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions.jvmTarget = "1.8"
  buildFeatures {
    compose = true

    // Disable unused AGP features
    buildConfig = false
    aidl = false
    renderScript = false
    resValues = false
    shaders = false
  }
  composeOptions.kotlinCompilerExtensionVersion = "1.4.7"
  packagingOptions {
    resources.excludes.add("META-INF/licenses/**")
    resources.excludes.add("META-INF/AL2.0")
    resources.excludes.add("META-INF/LGPL2.1")
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.core:core-ktx:1.10.1")
  implementation("androidx.fragment:fragment-ktx:1.5.7")
  implementation("com.google.android.material:material:1.9.0")
  implementation("androidx.activity:activity-compose:1.6.1")
  implementation("androidx.compose.runtime:runtime:1.3.3")
  implementation("androidx.compose.runtime:runtime-livedata:1.3.3")
  implementation("androidx.compose.ui:ui:1.3.3")
  implementation("androidx.compose.foundation:foundation:1.3.1")
  implementation("androidx.compose.foundation:foundation-layout:1.3.1")
  implementation("androidx.compose.material:material:1.3.1")
  implementation("androidx.compose.material:material-icons-extended:1.3.1")
  implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")

  androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
  androidTestImplementation("androidx.compose.ui:ui-test:1.3.3")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")

  debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")
}