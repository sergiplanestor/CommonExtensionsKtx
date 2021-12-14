plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18")
    implementation(kotlin("gradle-plugin", version = "1.5.31"))
}