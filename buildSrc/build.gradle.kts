plugins {
    `kotlin-dsl`
}

repositories {

    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation(kotlin("gradle-plugin", version = "1.5.31"))
}