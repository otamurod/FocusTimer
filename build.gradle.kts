// Top-level build file. Plugins are declared here (apply false) and applied in :app.
// AGP 9 has built-in Kotlin, so the org.jetbrains.kotlin.android plugin is NOT used.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
