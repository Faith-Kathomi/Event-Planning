// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
      id("com.google.gms.google-services") version "4.4.2" apply false
}
buildscript {
    dependencies {
        // Google services classpath
        classpath ("com.google.gms:google-services:4.4.2") // Ensure you have the correct version
        classpath ("com.android.tools.build:gradle:8.1.2") // Example version
        // Firebase if used
    }
}