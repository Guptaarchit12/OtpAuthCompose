// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Root build.gradle.kts
plugins {
    // DO NOT put 'version "8.6.0"' here. Use alias only.
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
}