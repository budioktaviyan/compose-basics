@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  namespace = "id.android.basics.compose"
  compileSdk = 33
  buildToolsVersion = "34.0.0"

  defaultConfig {
    applicationId = "id.android.basics.compose"
    minSdk = 30
    targetSdk = 33
    versionCode = 1
    versionName = "1.0.0"

    vectorDrawables.useSupportLibrary = true
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  testOptions {
    unitTests.isIncludeAndroidResources = true
  }
  sourceSets {
    named("androidTest") {
      java.srcDir("src/sharedTest/java")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  buildFeatures.compose = true
  composeOptions.kotlinCompilerExtensionVersion = "1.4.8"
  packaging {
    resources.excludes.add("/META-INF/AL2.0")
    resources.excludes.add("/META-INF/LGPL2.1")
  }
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation("androidx.activity:activity-ktx:1.7.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
  implementation("androidx.compose.runtime:runtime:1.4.3")
  implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
  implementation("androidx.compose.ui:ui:1.4.3")
  implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
  implementation("androidx.compose.foundation:foundation:1.4.3")
  implementation("androidx.compose.foundation:foundation-layout:1.4.3")
  implementation("androidx.compose.material:material:1.4.3")
  implementation("androidx.compose.material:material-icons-extended:1.4.3")
  implementation("androidx.compose.animation:animation:1.4.3")
  implementation("androidx.navigation:navigation-compose:2.6.0")
  implementation("com.google.accompanist:accompanist-insets:0.30.1")
  implementation("com.google.accompanist:accompanist-swiperefresh:0.30.1")
  implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
  implementation("androidx.core:core-ktx:1.10.1")

  testImplementation("org.robolectric:robolectric:4.10.3")

  androidTestImplementation("androidx.test:rules:1.5.0")
  androidTestImplementation("androidx.test:runner:1.5.2")
  androidTestImplementation("androidx.compose.ui:ui-test:1.4.3")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")

  debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    // Treat all Kotlin warnings as errors (disabled by default)
    allWarningsAsErrors = project.properties.getOrDefault("warningsAsErrors", false) as Boolean

    // Enable experimental coroutines APIs, including Flow
    freeCompilerArgs.plus("-opt-in=kotlin.RequiresOptIn")
    freeCompilerArgs.plus("-opt-in=kotlin.Experimental")

    jvmTarget = "1.8"
  }
}

tasks.withType<Test> {
  systemProperty("robolectric.logging", "stdout")
}